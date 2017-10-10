import java.time.{LocalDateTime, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.util.UUID

object Special {
  //    implicit val ZonedDateTimeWrites: Writes[ZonedDateTime] = Writes.temporalWrites[ZonedDateTime,
  //        DateTimeFormatter](DateTimeFormatter.ISO_OFFSET_DATE_TIME)

  def main(args: Array[String]): Unit = {
    /*var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    println(ZonedDateTime.now().format(formatter))
    formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    println(ZonedDateTime.now().format(formatter))
    println("123.456.789".split("\\.").last)
  */
    //      val sourceStr = ""
    //      val regex= "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"
    //      println(sourceStr.matches(regex))
    //      val ui = UUID.fromString(sourceStr)

    //      val strs = Seq("1","2","3")
    //      println(strs.mkString("','"))

    Some(5).map {
      case x if x == 4 => println(x)
      case _ => println("default")//match error without it
    }
  }
}
