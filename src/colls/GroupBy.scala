package colls

/**
  * Created by twr143 on 01.06.2021 at 11:59.
  */
object GroupBy {
  case class Car(serial: String, year: Int)
  val cars = Seq(Car("a123bb77", 2010),
    Car("c123dd45", 2013),
    Car("e456ff77", 2016))

  def main(args: Array[String]): Unit = {
    val gr = cars.groupBy(c => c.serial.substring(6))
    println(gr)
    println(gr.map { case (reg, seq) => {
  
      val a = seq.map(_.year).toArray
      if (a.size%2==0)
        (reg,(a(a.size/2-1)+a(a.size/2))/2)
        else 
        (reg, a(a.size/2))
    }
    })
  }
}
