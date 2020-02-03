package priceprocessing

import org.apache.log4j.Logger
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession


object Main {
  lazy val log = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("PriceProcessing")
      .getOrCreate()

    import spark.implicits._

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "revan-Virtual-Machine:9092")
      .option("subscribe", "test")
      .load()
    val query = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
      .writeStream
      .format("console")
      .start()
    df.printSchema()

    query.awaitTermination(20000)
  }


}
