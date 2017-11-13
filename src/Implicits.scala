import Greeter._

/*
created by Ilya Volynin at 09.11.17
*/
object Implicits {
//  import Impl._
  implicit val prompt = new PreferredPrompt("Yes, master> ")
  implicit val drink = new PreferredDrink("tea")
  def main(args: Array[String]): Unit = {
    greet("Ilya")
  }
}

class PreferredPrompt(val preference: String)
class PreferredDrink(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt,
                          drink: PreferredDrink) = {

    println("Welcome, " + name + ". The system is ready.")
    print("But while you work, ")
    println("why not enjoy a cup of " + drink.preference + "?")
    println(prompt.preference)

  }
}
object Impl{
  implicit def prompt = new PreferredPrompt("Yes, master> ")
  implicit def drink = new PreferredDrink("tea")
}


//object Impl extends Impl


