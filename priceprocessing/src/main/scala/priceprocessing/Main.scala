package priceprocessing

import org.apache.log4j.Logger
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Encoders, SaveMode, SparkSession}
import org.apache.spark.sql.streaming.OutputMode
import priceprocessing.model.Room


object Main {
  lazy val log = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("PriceProcessing")
      .config("spark.cassandra.connection.host", "127.0.0.1")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    import spark.implicits._
    import com.datastax.spark.connector._
    import com.datastax.spark.connector.streaming._

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
      .join(ratesDF, Seq("hotel_name"))

      .withColumn("total_price", col("price") * col("rate"))
      .withColumn("current_timestamp", current_timestamp())

    ratesDF.printSchema()
    ratesDF.show()

    roomWithAdjustedPriceDF.printSchema()
    kafkaDF.printSchema()

    val consoleStream = roomWithAdjustedPriceDF.writeStream
      .foreachBatch((batchDF, batchId) =>
        batchDF.write
          .format("org.apache.spark.sql.cassandra")
          .mode(SaveMode.Append)
          .option("spark.cassandra.output.consistency.level", "ANY")
          .option("table", "room")
          .option("keyspace","hotel_streaming")
          .save())
      .option("checkpointLocation", "./checkpoint_folder/")
      .start()
    consoleStream.awaitTermination(200000)
  }


}
