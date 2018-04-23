package colls

/*
created by Ilya Volynin at 13.03.18
*/
object Maps1 {
  def main(args: Array[String]): Unit = {
    val map1 = Map(1 -> 9 , 2 -> 20)
    val map2 = Map(1 -> 100, 3 -> 300)

    val merged = map1 ++ map2.map{ case (k,v) => k -> (v + map1.getOrElse(k,0)) }
    println(merged)
  }
}
