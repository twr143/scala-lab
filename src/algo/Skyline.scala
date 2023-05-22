package algo
import scala.collection.immutable.TreeSet
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 25.11.2019 at 11:17.
  */
object Skyline {

  case class Rect(l: Int, r: Int, h: Int)

  def getSkyline(buildings: Array[Array[Int]]): List[List[Int]] = {
    if (buildings.length == 0) return List.empty
    //there are two treesets: top and bottom
    implicit val orderingForTopTreeSet = new Ordering[Rect] {
      def compare(o1: Rect, o2: Rect): Int =
        if (o1.l != o2.l) o1.l - o2.l
        else if (o1.h != o2.h) o2.h - o1.h else o2.r - o1.r
    }
    val orderingForBottomTreeSet = new Ordering[Rect] {
      def compare(o1: Rect, o2: Rect): Int =
        if (o1.h != o2.h) o2.h - o1.h else if (o1.r != o2.r) o2.r - o1.r else
          o2.l - o1.l
    }
    var topTreeSet = buildings.map(b => Rect(b(0), b(1), b(2)))
    var bottomTreeSet = TreeSet.empty[Rect](orderingForBottomTreeSet)
    var l, r = Int.MinValue
    var h = 0
    val resultList = ArrayBuffer[List[Int]]()
    while (topTreeSet.nonEmpty || bottomTreeSet.nonEmpty) {
      if (topTreeSet.nonEmpty && topTreeSet.head.l <= r) {
        if (topTreeSet.head.h > h) {
          if (topTreeSet.head.r < r)
            bottomTreeSet += Rect(l, r, h)
          l = topTreeSet.head.l
          r = topTreeSet.head.r
          h = topTreeSet.head.h
          resultList += List(l, h)
        } else if (topTreeSet.head.r > r)
          bottomTreeSet += Rect(topTreeSet.head.l, topTreeSet.head.r, topTreeSet.head.h)
        topTreeSet = topTreeSet.tail
      } else if (bottomTreeSet.nonEmpty) {
        if (bottomTreeSet.head.r > r) {
          l = r
          r = bottomTreeSet.head.r
          if (bottomTreeSet.head.h < h) {
            h = bottomTreeSet.head.h
            resultList += List(l, h)
          }
        }
        bottomTreeSet = bottomTreeSet.tail
      } else {
        if (h > 0) resultList += List(r, 0)
        l = topTreeSet.head.l
        r = topTreeSet.head.r
        h = topTreeSet.head.h
        resultList += List(l, h)
        topTreeSet = topTreeSet.tail
      }
    }
    if (h > 0) resultList += List(r, 0)
    resultList.toList
  }

  def main(args: Array[String]): Unit = {
    println(getSkyline(Array(Array(0, 2, 3))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 3, 4))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 3, 2))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 5, 4), Array(2, 4, 4))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 4, 3))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 3, 4), Array(1, 5, 2))))
    println(getSkyline(Array(Array(0, 2, 3), Array(1, 4, 4), Array(3, 6, 3), Array(5, 7, 4))))
    println(getSkyline(Array(Array(3, 7, 8), Array(3, 8, 7))))

  }
}
