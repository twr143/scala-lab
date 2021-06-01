package A2021May

/**
  * Created by twr143 on 09.05.2021 at 12:09.
  */
object Entry01 {

  def main(args: Array[String]): Unit = {
    val tt = List.tabulate(3)(a => (a + 1) * (a + 1))
    tt.foreach(k => println(s"[$k]"))
  }
}
