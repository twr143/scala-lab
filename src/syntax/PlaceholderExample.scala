package syntax
object PlaceholderExample extends App{
}
trait A {
  def process[A](f: A => Unit) = f

  val set: Set[_ => Unit]
//  set.foreach(process _) // Error
  set.foreach(process(_)) // No Error
}
