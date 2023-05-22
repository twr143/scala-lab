package A2021May

import scala.collection.immutable.ListMap

/**
  * Created by twr143 on 09.05.2021 at 12:09.
  */
object Entry01 {

  def main(args: Array[String]): Unit = {
     val det = ListMap("1" -> 123,"5" -> "678")
     println(formatTransactionDetailes(det))
  }
  private def formatTransactionDetailes(detailes: ListMap[String, Any]): String = {
    val result = detailes.foldLeft("") { case (s, (k, v)) => s"$s$k: ${v.toString}\n" }
    if (result.nonEmpty) result.substring(0, result.length - 1) else result
  }

}
