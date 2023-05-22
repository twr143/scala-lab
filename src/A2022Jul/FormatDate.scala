package A2022Jul

import A2022Jul.Interpol.s1

object FormatDate {

  val v = "2021-07-22T09:49:53.552+0600"
  def main(args: Array[String]): Unit = {
     println(v.slice(0, v.indexOf("T")))
  }
}
