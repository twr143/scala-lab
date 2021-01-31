package oct2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 05.10.2020 at 11:30.
  */
object Underground {

  lazy val rides = mutable.Map.empty[String, mutable.ListBuffer[Int]]
  lazy val tempting = mutable.Map.empty[Int, (String, Int)]

  def checkIn(id: Int, stationName: String, t: Int) {
    tempting += id -> (stationName, t)
  }

  def checkOut(id: Int, stationName: String, t: Int) {
    val checkedIn = tempting.remove(id).get
    if (!rides.contains(keystr(checkedIn._1, stationName)))
      rides += keystr(checkedIn._1, stationName) -> mutable.ListBuffer(t - checkedIn._2)
    else
      rides(keystr(checkedIn._1, stationName)) += (t - checkedIn._2)
  }

  def getAverageTime(startStation: String, endStation: String): Double = {
    if (rides.contains(keystr(startStation, endStation))) {
      val lb = rides(keystr(startStation, endStation))
      lb.sum.toDouble / lb.size.toDouble
    } else 0
  }
  def keystr(startStation: String, endStation: String): String =
        startStation + ">>" + endStation

  def main(args: Array[String]): Unit = {

  }
}
