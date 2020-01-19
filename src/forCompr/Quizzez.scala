package forCompr

/**
  * Created by Ilya Volynin on 04.01.2020 at 11:19.
  */
object Quizzez {

  
  def main(args: Array[String]): Unit = {
    val a = List(10, 15, 20)
    (for {
      x <- a
      y <- List(x)
      z: Int <- List(y) if z % 2 == 0
    } yield s"z: ${z + 1}").foreach(println)


    a.flatMap(x => List(x).flatMap(y =>
      List(y).filter(z => z % 2 == 0))).
      map(z => s"z: ${z + 1}").foreach(println)
    //    a.withFilter(_ % 2 == 0)

    //    a flatMap { x:Int =>
    //      x flatMap { y:Int =>
    //        y withFilter (_ == 2) map { z =>
    //          z + 1
    //        }
    //      }
    //    }
  }
}