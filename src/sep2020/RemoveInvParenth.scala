package sep2020


/**
  * Created by Ilya Volynin on 12.09.2020 at 15:56.
  */
object RemoveInvParenth {
  def removeInvalidParentheses(s: String): List[String] = {
    val ss = s.replaceAll("^([a-z]*)\\)*", "$1")
      .replaceAll("\\(*([a-z]*)$", "$1")
      .replaceAll("^(\\(\\))\\)*", "$1")
    if (ss.length == 0) return List("")
    if (ss.length == 1) {
      return if (ss != "(" && ss != ")") List(ss) else List("")
    }
    val toRemove = ParenthToRemove(ss)
    val lr = (removeFromLeft(ss, 0, "", 0, 0, 0, toRemove)
      ).toList
    if (lr.isEmpty) return List("")
    lr
  }

  def removeFromLeft(s: String, i: Int, sPart: String, cntLeft: Int, cntRight: Int, cntCurr: Int, cntToRemove: Int): Set[String] = {
    if (cntCurr > cntToRemove) Set.empty
    else if (i == s.length)
      if (cntLeft == cntRight && sPart.length == s.length - cntToRemove) Set(sPart) else Set.empty 
    else if (s(i) == '(')
      removeFromLeft(s, i + 1, sPart + '(', cntLeft + 1, cntRight, cntCurr, cntToRemove) ++
        removeFromLeft(s, i + 1, sPart, cntLeft, cntRight, cntCurr + 1, cntToRemove)
    else if (s(i) == ')')
      if (cntLeft > cntRight)
        removeFromLeft(s, i + 1, sPart + ')', cntLeft, cntRight + 1, cntCurr, cntToRemove) ++
          removeFromLeft(s, i + 1, sPart, cntLeft, cntRight, cntCurr + 1, cntToRemove)
      else
        removeFromLeft(s, i + 1, sPart, cntLeft, cntRight, cntCurr + 1, cntToRemove)
    else
      removeFromLeft(s, i + 1, sPart + s(i), cntLeft, cntRight, cntCurr, cntToRemove)
  }


  def ParenthToRemove(s: String): Int = {
    var cnt, balance = 0
    for (i <- 0 to s.length - 1) {
      if (s(i) == ')' && balance == 0) cnt += 1
      else if (s(i) == '(') balance += 1
      else if (s(i) == ')') balance -= 1
    }
    balance = 0
    for (i <- s.length - 1 to 0 by -1) {
      if (s(i) == '(' && balance == 0) cnt += 1
      else if (s(i) == ')') balance += 1
      else if (s(i) == '(') balance -= 1
    }
    cnt
  }

  def main(args: Array[String]): Unit = {
    println(removeInvalidParentheses("()())()"))
    println(removeInvalidParentheses("())((()))x)(v()(h"))
  }
}
