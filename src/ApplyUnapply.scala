import scala.util.Random
object ApplyUnapply {
  def main(args: Array[String]): Unit = {
    createAndPrintCustomer("Ilya")
    createAndPrintCustomer("")
     val b = B("345")
  }
  def createAndPrintCustomer(name:String):Unit ={
    val customer1ID = CustomerIDAux(name)
    customer1ID match {
      case CustomerIDAux(name) => println(name)
      case _ => println(" non valid customer")
    }
  }
}
case class CustomerID(id: String)
object CustomerIDAux{
  def apply(id: String): CustomerID = {
    println("entered into apply")
    CustomerID(s"$id--${Random.nextLong()}")
  }

  def unapply(arg: CustomerID): Option[String] ={
    val name = arg.id.split("--").head
    if (name.nonEmpty) Some(name) else None
  }
}
case class M(a: Seq[String] )
case class B(some: String){
  def field():Int = 1
  val constant = 2
}
object MAux {
  def apply(a: String*): M = M(a) // makes a M from an As.
  def unapplySeq(m: M): Option[Seq[String]] = Some(m.a) // retrieve the As from the M
}