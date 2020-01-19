package algo.design.autocomplete

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 19.01.2020 at 10:48.
  */
class AutocompleteSystem(_sentences: Array[String], _times: Array[Int]) {
  var current = mutable.Map[Char, mutable.TreeMap[String, Int]]()
  var index = 0
  while (index < _sentences.length) {
    adjust(current, _sentences(index)(0), mutable.TreeMap[String, Int]())(_ += ((_sentences(index), _times(index))))
    index += 1
  }
  var searchStr = ""

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def input(c: Char): List[String] = {
    if (c != '#') {
      searchStr += c
      if (current.contains(searchStr(0)))
        current(searchStr(0)).filter { case (k, _) => if (searchStr.length <= k.length) k.substring(0, searchStr.length).equalsIgnoreCase(searchStr) else false }
          .toList.sortWith { case ((k1, v1), (k2, v2)) => if (v1 != v2) v2 < v1 else k1 < k2 }.take(3).map(_._1)
      else Nil
    } else {
      adjust(current, searchStr(0), mutable.TreeMap[String, Int]())(identity)
      adjust(current(searchStr(0)), searchStr, 0)(_ + 1)
      searchStr = ""
      Nil
    }
  }

  def showCurrent = current

}
