package priceprocessing

import org.apache.log4j.Logger
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Encoders
import priceprocessing.model.Room


object Main {
  lazy val log = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("PriceProcessing")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    import spark.implicits._
    //TODO: move it from here to reliable storage
    val ratesDF = broadcast(spark.read.json("./rates.json"))

    val kafkaDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "revan-Virtual-Machine:9092")
      .option("subscribe", "test")
      .load()

    val roomWithAdjustedPriceDF = kafkaDF.select(
      from_json(col("value").cast("string"),
        Encoders.product[Room].schema).as("j_struct"))
      .select("j_struct.*")
      .join(ratesDF, Seq("hotelName"))
      .withColumn("totalPrice", col("price") * col("rate"))
      .withColumn("currentTimestamp", current_timestamp())

    ratesDF.printSchema()
    ratesDF.show()

    roomWithAdjustedPriceDF.printSchema()
    kafkaDF.printSchema()

    val consoleStream = roomWithAdjustedPriceDF.writeStream
//      .format("console")
      .format("org.apache.spark.sql.cassandra")
      .option("spark.cassandra.connection.host", "localhost:9042")
      .option("table", "room")
      .option("keyspace","hotel_streaming")
      .start()
    consoleStream.awaitTermination(200000)
  }


}
