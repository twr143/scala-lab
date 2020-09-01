package aug2020

/**
  * Created by Ilya Volynin on 01.09.2020 at 9:57.
  */
object MedianOf2Sorted {
  def firstIndGreaterThan(nums: Array[Int], a: Int): Int = {
    if (nums(0) >= a) return 0
    if (nums.last < a) return -1
    fIGThan(nums, a, 0, nums.length - 1)
  }

  def fIGThan(nums: Array[Int], a: Int, l: Int, r: Int): Int = {
    if (r - l < 4) {
      if (r == l) {
        if (nums(l) >= a) l else -1
      } else if (r == l + 1) {
        if (nums(l) >= a) l else if (nums(l + 1) >= a) l + 1 else -1
      } else if (r == l + 2) {
        if (nums(l) >= a) l else if (nums(l + 1) >= a) l + 1 else if (nums(l + 2) >= a) l + 2 else -1
      } else {
        if (nums(l) >= a) l else if (nums(l + 1) >= a) l + 1 else if (nums(l + 2) >= a) l + 2 else if (nums(l + 3) >= a) l + 3 else -1
      }
    } else {
      val m = (l + r) / 2
      if (nums(m) >= a) fIGThan(nums, a, l, m) else fIGThan(nums, a, m, r)
    }
  }

  def lastIndLessThan(nums: Array[Int], a: Int): Int = {
    if (nums(0) > a) return -1
    if (nums.last <= a) return nums.length - 1
    lILThan(nums, a, 0, nums.length - 1)
  }

  def lILThan(nums: Array[Int], a: Int, l: Int, r: Int): Int = {
    if (r - l < 4) {
      if (r == l) {
        if (nums(r) <= a) r else -1
      } else if (r == l + 1) {
        if (nums(r) <= a) r else if (nums(r - 1) <= a) r - 1 else -1
      } else if (r == l + 2) {
        if (nums(r) <= a) r else if (nums(r - 1) <= a) r - 1 else if (nums(r - 2) <= a) r - 2 else -1
      } else {
        if (nums(r) <= a) r else if (nums(r - 1) <= a) r - 1 else if (nums(r - 2) <= a) r - 2 else if (nums(r - 3) <= a) r - 3 else -1
      }
    } else {
      val m = (l + r) / 2
      if (nums(m) <= a) lILThan(nums, a, m, r) else lILThan(nums, a, l, m)
    }
  }

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val n1 = nums1.length
    val n2 = nums2.length
    val n1Even = n1 % 2 == 0
    val n2Even = n2 % 2 == 0
    val m1 = n1 / 2
    val m2 = n2 / 2
    if (n1 == 0)
      if (n2Even) (nums2(m2 - 1).toDouble + nums2(m2).toDouble) / 2 else nums2(m2).toDouble
    else if (n2 == 0)
      if (n1Even) (nums1(m1 - 1).toDouble + nums1(m1).toDouble) / 2 else nums1(m1).toDouble
    else if (nums1(m1) == nums2(m2)) nums1(m1).toDouble
    else {
      val g = if (nums1(m1) > nums2(m2)) nums1 else nums2
      val s = if (nums1(m1) > nums2(m2)) nums2 else nums1
      val gm = if (nums1(m1) > nums2(m2)) m1 else m2
      val sm = if (nums1(m1) > nums2(m2)) m2 else m1
      val lilt = lastIndLessThan(s, g(gm))
      val figt = firstIndGreaterThan(g, s(sm))
      val igShift = gm + (s.length - lilt - 1)
      val isShift = sm + (if (figt > -1) figt else 0)
      mergeDescend(g, s, gm, lilt, igShift)
    }
  }

  def mergeDescend(g: Array[Int], s: Array[Int], gm: Int, lilt: Int, igShift: Int): Double = {
    var processed = 0
    val medium = (s.length + g.length) / 2
    var gInd = gm
    var sInd = lilt
    var result1, result2 = 0
    while (processed + igShift <= medium + 1) {
      if (g(gInd) > s(sInd)) {
        result2 = result1
        result1 = g(gInd)
        gInd -= 1
      } else {
        result2 = result1
        result1 = s(sInd)
        sInd -= 1
      }
      println(s"r1 = $result1, r2 = $result2")
      processed += 1
    }
    if ((s.length + g.length) % 2 == 0) (result1.toDouble + result2.toDouble) / 2 else result2.toDouble
  }

  def main(args: Array[String]): Unit = {

    //    println(firstIndGreaterThan(Array(1, 2, 6, 7, 8), 5))
    //    println(lastIndLessThan(Array(6, 7, 8), 5))
//    val nums1 = Array(1, 4, 8, 10, 13, 14, 15)
//    val nums2 = Array(2, 3, 5, 6, 7, 9, 11, 12)
//    println(findMedianSortedArrays(nums1, nums2))
    val nums3 = Array(1, 3)
    val nums4 = Array(2, 4, 5, 6, 7)
    println(findMedianSortedArrays(nums3, nums4))
  }
}
