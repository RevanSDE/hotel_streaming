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

    val kafkaDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "revan-Virtual-Machine:9092")
      .option("subscribe", "test")
      .load()

    val roomWithAdjustedPriceDF = kafkaDF.select(
      from_json(col("value").cast("string"),
      Encoders.product[Room].schema))

    roomWithAdjustedPriceDF.printSchema()
    kafkaDF.printSchema()

    val consoleStream = roomWithAdjustedPriceDF.writeStream
      .format("console")
      .start()
    consoleStream.awaitTermination(20000)
  }


}
