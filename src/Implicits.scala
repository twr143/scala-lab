/*
created by Ilya Volynin at 09.11.17
*/
object Implicits {
//  import Person._
  def main(args: Array[String]): Unit = {
    val johnDoe = Person("John", "Doe", 32)
    val jdName:String = johnDoe
    println(s"name: $jdName")
  }
}
case class Person(name: String, surname: String, age: Int)
object Person {
  implicit def pToString(p: Person):String = p.name + " " + p.surname + ", age " + p.age
//  implicit def pToString2(p: Person):String = p.name + " " + p.surname + ", age " + p.age
  implicit def pToInt(p: Person):Int = p.age
}

