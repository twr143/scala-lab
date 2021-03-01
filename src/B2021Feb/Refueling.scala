package B2021Feb

import scala.collection.mutable

/**
  * Created by twr143 on 24.02.2021 at 10:40.
  */
object Refueling {

  def minRefuelStops(target: Int, startFuel: Int, stations: Array[Array[Int]]): Int = {
    val s = mutable.Stack[(Int, Int)]()
    if (stations.length == 0) return if (target <= startFuel) 0 else -1
    var curIndex = 0
    var curFuel = startFuel
    var curDistance = 0

    var cnt = 0
    while (curIndex < stations.length) {
      if (curIndex == stations.length - 1 && s.isEmpty) {
        return (if (target <= curFuel) cnt
        else if (stations(curIndex)(0) <= curFuel && target <= curFuel + stations(curIndex)(1)) cnt + 1
        else -1)
      } else {
        var tmpcurFuel = curFuel
        while (stations(curIndex + 1)(0) <= curFuel) {
          tmpcurFuel += (stations(curIndex)(1) - stations(curIndex)(0))
          curIndex += 1
          s.push((curIndex, tmpcurFuel))
        }
        if (s.nonEmpty) {
          if (curIndex < stations.length) {
            val popped = s.pop()

          }
        }

      }

    }
    -1
  }

}
