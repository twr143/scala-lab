package algo.design.autocomplete2

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 19.01.2020 at 12:33.
  */
class AutocompleteSystem(_sentences: Array[String], _times: Array[Int]) {
  private[this] val root = new Trie()

  private[this] val dict = mutable.Map.empty[String, Int]
  for (i <- 0 until _sentences.length) {
    dict.update(_sentences(i), _times(i))
    root.insert(_sentences(i), _sentences(i), _times(i))
  }


  private[this] var entered: String = ""

  def input(c: Char): List[String] = {
    if (c == '#') {
      dict.update(entered, dict.getOrElse(entered, 0) + 1)
      root.insert(entered, entered, dict(entered))
      entered = ""
      Nil
    } else {
      entered = entered + c
      root.startWith(entered)
    }
  }
}

class Trie() {
  // Could use a reverse index to save footprint
  private[this] val top3 = new Top3Stack()

  // The character will only be lower-case letters ('a' to 'z'),
  // blank space (' ') or a special character ('#').
  private[this] val children = mutable.Map.empty[Char, Trie]

  def insert(suffix: String, word: String, time: Int): Unit = {
    top3.push(word, time)

    if (suffix.nonEmpty) {
      val c = suffix.charAt(0)
      if (!children.contains(c)) {
        children.update(c, new Trie())
      }

      children(c).insert(suffix.substring(1), word, time)
    }
  }

  def startWith(prefix: String): List[String] = {
    if (prefix.isEmpty) {
      top3.peekAll
    } else {
      val c = prefix.charAt(0)
      if (children.contains(c)) {
        children(c).startWith(prefix.substring(1))
      } else {
        Nil
      }
    }
  }
}

case class CountedWord(s: String, v: Int)
object CountedWord {
  // reverse ordering
  implicit val ordering: Ordering[CountedWord] = Ordering.fromLessThan {
    (a, b) =>
      if (a.v == b.v) {
        a.s < b.s
      } else {
        a.v > b.v
      }
  }
}

class Top3Stack() {
  var records = mutable.Map.empty[String, Int]

  def toWords: List[CountedWord] = records.map {
      case (w, t) => CountedWord(w, t)
    }.toList.sorted

  def push(word: String, time: Int): Unit = {
    records.update(word, time)

    val sorted = toWords.slice(0, 3)

    records.clear

    sorted.foreach {
      case CountedWord(s, v) => records.update(s, v)
    }
  }

  def peekAll: List[String] = toWords.map(_.s)
}
