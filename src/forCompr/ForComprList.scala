package forCompr
/*
created by Ilya Volynin at 31.01.18
*/
object ForComprList {
  def main(args: Array[String]): Unit = {
    val lara = Person("Lara", false)
    val bob = Person("Bob", true)
    val lyossha = Person("Lyossha", true)
    val natasha = Person("Natasha", false)
    val julie = Person("Julie", false, lara, bob)
    val ilya = Person("Ilya", false, lyossha, natasha)
    val persons = List(lara, bob, julie, ilya, lyossha, natasha)
    //val motherKid = for (p <- persons; if !p.isMale; c <- p.children)
    // yield (p.name, c.name)
    //println(motherKid)
    val motherFatherKid2 = for {
      p <- persons //if false
      c <- p.children
      if p.children.size > 2
    } yield (p.name, c.name)
    println(motherFatherKid2)
  }
}
case class Person(name: String, isMale: Boolean, children: Person*)
