import java.time.{ZoneId, ZoneOffset, ZonedDateTime}

/*
created by Ilya Volynin at 15.11.17
*/
object Implicits3 {
  implicit def dateTime(implicit zone: ZoneId): ZonedDateTime = ZonedDateTime.now(zone)

  def printCurrentDateTime(implicit dt: ZonedDateTime) = println(dt.toString)
  def main(args: Array[String]): Unit = {

    implicit val utc = ZoneOffset.UTC

    printCurrentDateTime
  }
}
