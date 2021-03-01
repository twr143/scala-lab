package B2021Feb

import scala.collection.mutable

/**
  * Created by twr143 on 23.02.2021 at 14:28.
  */
object ValidateStackSeqs {
  def validateStackSequences(pushed: Array[Int], popped: Array[Int]): Boolean = {
    val s = mutable.Stack[Int]()
    var pPushed = 0
    var pPopped = 0
    while (pPushed < pushed.length) {
      if (pushed(pPushed) == popped(pPopped)) {
        pPopped += 1
      }
      else if (s.nonEmpty && s.top == popped(pPopped)) {
        pPopped += 1
        s.pop()
        if (pushed(pPushed) == popped(pPopped)) pPopped += 1
        else pPushed -= 1
      }
      else s.push(pushed(pPushed))
      pPushed += 1
    }
    while (s.nonEmpty)
      if (s.pop() != popped(pPopped)) return false
      else pPopped += 1
    true
  }

  def main(args: Array[String]): Unit = {
    println(validateStackSequences(Array(9,5,0,8,1,6,2,7,4,3), Array(0,5,9,1,6,8,7,4,3,2)))
  }

}
