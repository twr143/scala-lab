package aug2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 25.08.2020 at 8:39.
  */
object FrogJump {
  def canCross(stones: Array[Int]): Boolean = {
    var jump = 0
    if (stones(1) != 1) return false
    val cache = Array.ofDim[Int](stones.length, stones.length)
    cC(stones, cache, 1, 1)
  }

  def cC(stones: Array[Int], cache: Array[Array[Int]],
         startIndex: Int, lastJump: Int): Boolean = {

//    println(s"startIndex=$startIndex, lastJump = $lastJump")
    if (startIndex == stones.length - 1) return true
    if (cache(startIndex)(lastJump) == -1) return false
    if (cache(startIndex)(lastJump) == 1) return true
    var k = 1
    var result = false
    while (startIndex + k < stones.length && stones(startIndex + k) -
      stones(startIndex) - lastJump <= 1) {
      //      if (k > 2)
      //        println(s"i=$startIndex,k = $k, Math.abs(stones(i) - stones(i - 1) - jump) = " +
      //          s"${Math.abs(stones(startIndex + k) - stones(startIndex) - lastJump)}")
      if (Math.abs(stones(startIndex + k) - stones(startIndex) - lastJump) <= 1 &&
        cC(stones, cache, startIndex + k, stones(startIndex + k) - stones(startIndex)))
        result = true

      k += 1
    }
    if (!result) cache(startIndex)(lastJump) = -1 else cache(startIndex)(lastJump) = 1
    result
  }

  def main(args: Array[String]): Unit = {
    //    println(canCross(Array(0, 1, 3, 5, 6, 8, 12, 17)))
    //    println()
    //    println(canCross(Array(0, 1, 2, 3, 4, 8, 9, 11)))
    //
    //    println(canCross(Array(0, 1, 3, 6, 10, 15, 16, 21)))
    //
        println(canCross(Array(0, 1, 2, 3, 4, 5, 8)))
            val la = Array.ofDim[Int](999)
            for (i <- 0 to 997)
              la(i) = i
            la(998) = 1035
            println(canCross(la))
//    println(canCross(Array(0, 1, 3, 6, 7)))
  }
}
