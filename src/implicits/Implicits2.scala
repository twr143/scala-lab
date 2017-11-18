package implicits

/*
created by Ilya Volynin at 13.11.17
*/
case class Address(no: Int, street: String, city: String, state: String, zip: String)
object LabelMakers {
  trait LabelMaker[T] {
    def toLabel(value: T): String
  }
  implicit object AddressLabelMaker extends LabelMaker[Address] {
    def toLabel(a: Address):String = {
      import a._
      "%d %s, %s, %s - %s".format(no, street, city, state, zip)
    }
  }
  implicit object StringLabelMaker extends LabelMaker[String] {
    def toLabel(s: String):String = s
  }
  /*
  // If this is not commented, the compiler complains about ambiguity

  implicit object AnotherStringLabelMaker extends LabelMaker[String] {
    def toLabel(s: String) = s.toUpperCase

  }
  */

  def makeLabel[T](t: T)(implicit lm: LabelMaker[T]):String = lm.toLabel(t)
}
object Implicits2 {
  def main(args: Array[String]) {
    val address = Address(42, "Monroe Street", "Denver", "CO", "80123")
    // importing makeLabel method only

    import LabelMakers.makeLabel
    // Both implicits are found, although only makeLabel should be available

    println(makeLabel(address))
    println(makeLabel("a String"))
    // This does not compile, and thats ok

    // error: not found: value StringLabelMaker
    //println(StringLabelMaker.getClass)

  }
}
