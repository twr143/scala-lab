package algo.validParantheses

import scala.collection.mutable.Stack

/**
  * Created by Ilya Volynin on 08.01.2020 at 14:53.
  */
object ValidParanth {
  def isValid(s: String): Boolean = {
    if (s.length % 2 == 1) return false
    val opened = Stack[Char]()
    s.foreach { c =>
      if (c == '(' || c == '[' || c == '{') opened.push(c)
      else if (opened.nonEmpty && opened.pop().matches(c)) ()
      else return false
    }
    opened.isEmpty
  }

  def longestValidParentheses(s: String): Int = {
    val unmatched = Stack[Int]() // indexes
    s.view.zipWithIndex.foreach { case (c: Char, i: Int) =>
      if (c == ')' && unmatched.nonEmpty && s(unmatched.top) == '(') unmatched.pop()
      else unmatched.push(i)
    }
    if (unmatched.nonEmpty) {
      var first = if (unmatched.top == s.length - 1) unmatched.pop() else s.length
      var second = 0
      var max = 0
      while (unmatched.nonEmpty) {
        second = unmatched.pop()
        max = math.max(max, first - second - 1)
        first = second
      }
      math.max(max, first)
    }
    else s.length
  }

  def lvp2(s: String): Int = {
    var left, right, max, cur = 0
    s.view.foreach { c =>
      if (c == '(') left += 1
      else if (c == ')') right += 1
      if (right > left) {
        left = 0
        right = 0
        max = math.max(max, cur)
      } else if (right == left) {
        cur = 2 * left
      }
    }
    left = 0
    right = 0
    var index = s.length - 1
    while (index > -1) {
      val c = s(index)
      if (c == '(') left += 1
      else if (c == ')') right += 1
      if (right < left) {
        left = 0
        right = 0
        max = math.max(max, cur)
      } else if (right == left) {
        cur = 2 * left
      }
      index -= 1
    }

    math.max(max, cur)

  }

  def checkValidWithStar(s: String): Boolean = {
    var left, right, nstars = 0
    s.view.foreach { c =>
      if (c == '(') left += 1
      else if (c == ')') right += 1
      else nstars += 1 //if (c == '*')
      if (right - left > nstars) return false
    }
    left = 0
    right = 0
    nstars = 0
    var index = s.length - 1
    while (index > -1) {
      val c = s(index)
      if (c == '(') left += 1
      else if (c == ')') right += 1
      else nstars += 1 //if (c == '*')
      if (left - right > nstars) return false
      index -= 1
    }
    true
  }

  def minAddToMakeValid(S: String): Int = {
    var left, right, leftmins, rightmins = 0
    S.view.foreach { c =>
      if (c == '(') left += 1
      else if (c == ')') right += 1
      if (right > left) leftmins = math.max(leftmins, right - left)
    }
    left = 0
    right = 0
    var index = S.length - 1
    while (index > -1) {
      val c = S(index)
      if (c == '(') left += 1
      else if (c == ')') right += 1
      if (left > right) rightmins = math.max(rightmins, left - right)
      index -= 1
    }
    leftmins + rightmins
  }

  implicit class Matcher(c: Char) {
    def matches(closing: Char): Boolean = {
      (c == '(' && closing == ')') ||
        (c == '[' && closing == ']') ||
        (c == '{' && closing == '}')

    }
  }

  def scoreOfParentheses(S: String): Int = {
    score(S, 0, S.length)
  }

  def score(s: String, begin: Int, end: Int): Int = {

    var left, right = 0
    var index, curLeft, curRight = begin
    var result = 0
    while (index < end) {
      val c = s(index)
      if (c == '(') left += 1
      else if (c == ')') right += 1
      curRight += 1
      if (right == left) {
        val delta = curRight - curLeft
        result +=
          (if (delta == 2) 1
          else if (delta == 4) 2
          else 2 * score(s, curLeft + 1, curRight - 1))
        curLeft = index + 1
        curRight = index + 1
      }
      index += 1
    }
    result
  }

  def main(args: Array[String]): Unit = {
    //    println(isValid("()"))
    //    println(isValid("(()()(){})"))
    //    println(isValid("{(()())(){})"))
    //    println(longestValidParentheses(")()())"))
    //    println(longestValidParentheses("(()"))
    //    println(longestValidParentheses("(())()"))
    //    println(lvp2(")()())"))
    //    println(lvp2("(()"))
    //    println(lvp2("(())()"))
    //    println(checkValidWithStar("*)()"))
//    println(minAddToMakeValid("())))(("))
    println(scoreOfParentheses("((())(()))"))
  }
}
