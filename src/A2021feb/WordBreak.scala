package A2021feb

import scala.collection.mutable

/**
  * Created by twr143 on 05.02.2021 at 14:14.
  */
object WordBreak {
  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val sSet = s.toCharArray.toSet
    val wSet = wordDict.flatMap(_.toCharArray).toSet
    if (sSet.diff(wSet).nonEmpty) return false
    val wd = filterComposites(wordDict)
    wb(s, wd)
  }

  def wb(s: String, wordDict: List[String]): Boolean = {
    val tasks = mutable.Queue[Int]()
    val memo = mutable.Map.empty[Int, List[Int]]
    tasks += 0
    while (tasks.nonEmpty) {
      val i = tasks.dequeue()
      if (i == s.length) return true
      tasks ++= wbList(s, wordDict, memo)(i)
    }
    false
  }

  def wbList(s: String, wordDict: List[String], memo: mutable.Map[Int, List[Int]]): Int => List[Int] = {
    var res = List.empty[Int]
    i => {
      if (memo.contains(i)) memo(i)
      else {
        wordDict.foreach(c => if (contains(s, c, i)) res = ((i + c.length)) :: res)
        memo += i -> res
        res
      }
    }
  }

  def filterComposites(wordDict: List[String]): List[String] = {
    val wSet = wordDict.foldLeft(mutable.Set.empty[String])((s, e) => s + e)
    val out = mutable.Set.empty[String]
    wordDict.foreach(w => if (!wb(w, out.toList)) out += w)
    out.toList
  }

  val cMemo = mutable.Map.empty[(String, String, Int), Boolean]

  def contains(s: String, candidate: String, start: Int): Boolean = {
    if (cMemo.contains((s, candidate, start))) cMemo(s, candidate, start)
    else {
      if (start + candidate.length > s.length) return false
      for (i <- 0 to candidate.length - 1)
        if (s(i + start) != candidate(i)) {
          cMemo += ((s, candidate, start)) -> false
          return false
        }
      cMemo += ((s, candidate, start)) -> true
      true
    }
  }

  def main(args: Array[String]): Unit = {
        val dict = List("apple", "pen")
        println(wordBreak("applepenapple", dict))
        val d2 = List("a", "b", "bbb", "bbbb")
        println(wordBreak("bb", d2))
        println(filterComposites(Array("aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba").toList))
//    val d3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//    val l3 = List("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba")
//    println(wordBreak(d3, l3))
  }
}
