package A2021Nov.domain

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class A {
    val b = Future{
      println("b initialized")
      1
    }
    def m(param : Int): Unit => Future[Int] = _ => b.map(a => {
       println(s"a: ${a+param}")
       a+param
    })


}
