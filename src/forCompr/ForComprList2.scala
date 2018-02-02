package forCompr

/*
created by Ilya Volynin at 02.02.18
*/
object ForComprList2 extends App {
  val lst = for {
    x <- List.tabulate(3)(i => i)
    y <- List.tabulate(3)(i => i) if false
    z <- List.tabulate(3)(i => i)
  } yield (x, y, z)

  println(lst)
}
