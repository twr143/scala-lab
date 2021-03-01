package B2021Feb

/**
  * Created by twr143 on 25.02.2021 at 14:51.
  */
object RearrangeWords {

  def arrangeWords(text: String): String = {
    val words = text.split("\\s")
    words(0) = words(0).toLowerCase()
    implicit val ord = Ordering.fromLessThan[(String, Int)]((f, s) => if (f._1.length != s._1.length) f._1.length < s._1.length else f._2 < s._2)
    val res = words.zipWithIndex.sorted.map(_._1).mkString(" ")
    res.capitalize
  }

  def findUnsortedSubarray(nums: Array[Int]): Int = {
    implicit val ord = Ordering.fromLessThan[(Int, Int)]((f, s) => if (f._1 != s._1) f._1 < s._1 else f._2 < s._2)
    var sorted = nums.zipWithIndex.sorted
    var l = 0
    var r = nums.length - 1
    while (l < sorted.length && sorted(l)._2 == l)
      l += 1
    while (r > l && sorted(r)._2 == r)
      r -= 1
    if (r > l) r - l + 1 else 0
  }


}
