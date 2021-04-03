package A20201Mar

/**
  * Created by twr143 on 07.03.2021 at 17:10.
  */

import scala.collection.mutable._

class Trie {

  case class Node(level: Int, c: Char, childs: Map[Char, Node] = Map.empty, words: ListBuffer[String] = ListBuffer.empty[String])
  var depth = 5
  val root = Node(0, '0')

  def insert(word: String) {
    ins(word, 0, root)
  }

  def ins(word: String, index: Int, root: Node): Unit = {
    if (index == word.length) root.words += word
    else if (index < depth && root.childs.contains(word(index)))
      ins(word, index + 1, root.childs(word(index)))
    else if (index < depth) {
      root.childs += word(index) -> Node(index, word(index))
      ins(word, index + 1, root.childs(word(index)))
    } else
      root.words += word
  }

  /** Returns if the word is in the trie. */
  def search(word: String): Boolean = {
    var c = root
    for (i <- 0 to depth - 1)
      if (i == word.length) return c.words.contains(word)
      else {
        if (!c.childs.contains(word(i))) return false
        c = c.childs(word(i))
      }
    c.words.contains(word)
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  def startsWith(prefix: String): Boolean = {
    var c = root
    for (i <- 0 to depth - 1)
      if (i == prefix.length) return c.childs.nonEmpty || c.words.filter(_.startsWith(prefix)).nonEmpty
      else {
        if (!c.childs.contains(prefix(i))) return false
        c = c.childs(prefix(i))
      }
    
    c.words.filter(_.startsWith(prefix)).nonEmpty

  }

}

