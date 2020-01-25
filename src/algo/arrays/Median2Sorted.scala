package algo.arrays

/**
  * Created by Ilya Volynin on 23.01.2020 at 15:10.
  */
object Median2Sorted {
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    var f, s = 0
    var cur = 0
    var curInd = 1
    val m = nums1.length
    val n = nums2.length
    val isEven = (m + n) % 2 == 0
    while (true) {
      if (curInd == (m + n) / 2 && isEven) {
        if (f < m && s < n)
          if (nums1(f) < nums2(s)) {
            cur = nums1(f); f += 1
          }
          else {
            cur = nums2(s); s += 1
          }
        else if (f == m) {
          cur = nums2(s); s += 1
        }
        else if (s == n) {
          cur = nums1(f); f += 1
        }
        return (cur.toDouble + EvenNext(f, s, nums1, nums2).toDouble) / 2
      }
      else if (curInd == (m + n + 1) / 2 && !isEven) {
        if (f < m && s < n)
          if (nums1(f) < nums2(s)) cur = nums1(f) else cur = nums2(s)
        else if (f == m) cur = nums2(s)
        else if (s == n) cur = nums1(f)
        return cur.toDouble
      }
      if (f < m && s < n)
        if (nums1(f) < nums2(s)) f += 1
        else s += 1
      else if (f == m) s += 1
      else if (s == n) f += 1
      curInd += 1
    }
    0
  }

  def EvenNext(f: Int, s: Int, nums1: Array[Int], nums2: Array[Int]): Int = {
    val m = nums1.length
    val n = nums2.length
    if (f < m && s < n)
      if (nums1(f) < nums2(s)) nums1(f) else nums2(s)
    else if (f == m) nums2(s)
    else nums1(f)
  }

  def main(args: Array[String]): Unit = {
    //    println(findMedianSortedArrays(Array(1, 3), Array(2)))
    //    println(findMedianSortedArrays(Array(1, 2), Array(3, 4)))
    println(findMedianSortedArrays(Array(),Array(1, 2, 3, 4)))
  }
}
