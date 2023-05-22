package A2022Jul

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, ZonedDateTime}

object TestClock {
  def main(args: Array[String]): Unit = {
    val fromatter =DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
    val zoneMsk = ZoneId.of("Europe/Moscow")
        val zdt = ZonedDateTime.now(zoneMsk).format(fromatter)
            val v = 86725/1000
       println(v)
    }

}
