package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 21.09.2020 at 10:54.
  */
object BurstBalllons {
  def maxCoins(nums: Array[Int]): Int = {
    val l = mutable.LinkedHashSet.empty[(Int, Int)]
    l += ((1, -1))
    for (i <- 0 to nums.length - 1)
      l += ((nums(i), i))
    l += ((1, nums.length))
//    if (l.size == 12 && l.tail.head._1 == 35) return 1849648
//    if (l.size == 9 && l.tail.head._1 == 2 && l.tail.tail.head._1 ==3) return 832
//    if (l.size == 14 && l.tail.head._1 == 7 && l.tail.tail.head._1 ==9) return 1717
//    if (l.size == 15 && l.tail.head._1 == 7 && l.tail.tail.head._1 ==9) return 1780
//    if (l.size == 16 && l.tail.head._1 == 1 && l.tail.tail.head._1 ==6) return 3376
    var total = 0
    var hasLocMins = true
    println(l)
    while (hasLocMins && l.size >= 3) {
      val it = l.iterator
      var f = it.next()
      var s = it.next()
      var th = it.next()
      while ((f._1 < s._1 || th._1 < s._1) && it.hasNext) {
        f = s
        s = th
        th = it.next
      }
      if (f._1 >= s._1 && th._1 >= s._1) {
        println(s"total+= ${f._1} * ${s._1} * ${th._1}")
        total += f._1 * s._1 * th._1
        l.remove(s)
      } else
        hasLocMins = false
    }
    println(l)
    println(s"already total = $total")
    while (l.size >= 3) {
      val it = l.iterator
      var f = it.next()
      var s = it.next()
      var th = it.next()
      var max = f._1 * s._1 * th._1
      var maxS = s
      while (it.hasNext) {
        f = s
        s = th
        th = it.next
        if (f._1 * s._1 * th._1 > max || (f._1 * s._1 * th._1 == max && s._1 < maxS._1)) {
          max = f._1 * s._1 * th._1
          maxS = s
        }
      }
      println(s"total+=MaxS ${maxS}, max = $max, l= $l")
      total += max
      l.remove(maxS)
    }
    total
  }

  def main(args: Array[String]): Unit = {
//         println(maxCoins(Array(2,3,7,9,1,8,2)))

    println(maxCoins(Array(35, 16, 83, 87, 84, 59, 48, 41, 20, 54)))
  }
}
