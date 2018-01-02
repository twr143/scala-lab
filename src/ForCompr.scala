/*
created by Ilya Volynin at 16.11.17
*/
object ForCompr {
  def main(args: Array[String]): Unit = {
    val lara = Person("Lara", false)
    val bob = Person("Bob", true)
    val julie = Person("Julie", false, lara, bob)
    val persons = List(lara, bob, julie)
    val motherKid = for (p <- persons; if !p.isMale; c <- p.children)
      yield (p.name, c.name)
    println(motherKid)
    val motherKid2 = for {
      p <- persons if false
      c <- p.children
    } yield (p.name, c.name)
    //println(motherKid2)
    val first = Option(2)
    val f1 = for {
      f <- first.flatMap{
        case 1 => None
        case x => Some(x+1)
      }
    } yield f
    println(f1)
  }
}
case class Person(name: String, isMale: Boolean, children: Person*)
