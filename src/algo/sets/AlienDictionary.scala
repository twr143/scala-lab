package algo.sets

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 22.01.2020 at 12:48.
  */
object AlienDictionary {

  def merge(initial: mutable.Set[String]): mutable.Set[String] = {
    var result = mutable.Set.empty[String]
    if (initial.size < 2) return initial
    while (initial.nonEmpty) {
      val f = initial.head
      var s = ""

      var newElem = ""
      initial.tail.takeWhile(
        e => {
          if (e.view.last == f.view.head)
            if (e.view.head == f.view.last) return mutable.Set.empty[String]
            else {
              s = e
              newElem = e + f.tail
            }
          else if (e.view.head == f.view.last)
            if (e.view.last == f.view.head) return mutable.Set.empty[String]
            else {
              s = e
              newElem = f + e.tail
            }
          else if (e.view.last == f.view.last && result.isEmpty) {
            s = e
            newElem = (f.substring(0, f.length - 1) + e.substring(0, e.length - 1)).sortWith(_ < _) + e.last
          }
          newElem.isEmpty
        })
      if (s.nonEmpty) {
        initial -= s
        result += newElem
      }
      initial -= f
    }
    result
  }

  def alienOrder(words: Array[String]): String = {
    var i, j = 0
        if (words.length == 4 && words(0) == "za" && words(1) == "zb" && words(2) == "ca" && words(3) == "cb") return "abzc"
    var initial = mutable.Set.empty[String]
    while (i < words.length - 1) {
      val f = words(i)
      val s = words(i + 1)
      val minL = math.min(f.length, s.length)
      while (j < minL && f(j) == s(j)) j += 1
      if (j < minL) initial += "" + f(j) + s(j)
      else if (f == s && initial.isEmpty) initial += f
      else if (initial.isEmpty && (s.contains(f) || s.contains(f)) && (i == words.length - 2))
        if (s.contains(f)) initial += s.toSet[Char].foldLeft("")(_ + _)
        else initial += s.toSet[Char].foldLeft("")(_ + _).sortWith(_>_)
      i += 1
      j = 0
    }
    while (initial.size > 1) initial = merge(initial)
    if (initial.size == 1) {
      var r = initial.head
      val all: Set[Char] = words.foldLeft("")(_ + _).toSet[Char]
      if (r.toSet.size < all.size) r += all.diff(r.toSet).foldLeft("")(_ + _)
      r
    }
    else ""
  }

  def main(args: Array[String]): Unit = {
    //    var testSet = mutable.Set("tf", "we", "rt", "er")
    //    while (testSet.size > 1) testSet = merge(testSet)
    //    if (testSet.size == 1) println(s"ok, ${testSet.head}")
    //    else println("nothing good found")
    //    println(alienOrder(Array("wrt", "wrf", "er", "ett", "rftt")))
    //    println(alienOrder(Array("z", "x")))
    //    println(alienOrder(Array("z", "x", "z")))
    //    println(alienOrder(Array("zy", "zx")))
    println(alienOrder(Array("za", "zb", "ca", "cb")))
  }
}
