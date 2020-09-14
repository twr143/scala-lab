package sep2020

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 14.09.2020 at 11:17.
  */
object CountSmaller {

  case class Node(value: Int) {
    var nDecendent = 1 // including itself
    var left: Node = null
    var right: Node = null

    def Add(v: Int, nSmaller: Int): Int = {
      nDecendent += 1;
      if (v > value) {
        val nS = nSmaller + (if (left != null) left.nDecendent else 0) + 1
        if (right != null) {
          right.Add(v, nS)
        } else {
          right = Node(v)
          nS
        }
      } else {
        if (left != null) {
          left.Add(v, nSmaller)
        } else {
          left = Node(v)
          nSmaller
        }
      }
    }
  }

  def countSmaller(nums: Array[Int]): List[Int] = {
    if (nums.length == 0) List.empty
    else {
      val ans = Array.ofDim[Int](nums.length)
      val root = Node(nums(nums.length - 1))
      for (i <- nums.length - 2 to 0 by -1)
        ans(i) = root.Add(nums(i), 0)
      ans.toList
    }
  }

  def main(args: Array[String]): Unit = {
    println(countSmaller(Array(5,2,6,1)))
  }

}
