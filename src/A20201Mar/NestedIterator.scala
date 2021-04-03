package A20201Mar

/*
  * Created by twr143 on 12.03.2021 at 12:49.
  */
class NestedIterator {
  class NestedInteger {
    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    def isInteger: Boolean = {
      false
    }

    // Return the single integer that this NestedInteger holds, if it holds a single integer
    def getInteger: Int = {
      0
    }

    // Set this NestedInteger to hold a single integer.
    def setInteger(i: Int) = {}

    // Return the nested list that this NestedInteger holds, if it holds a nested list
    def getList: Array[NestedInteger] = {
      Array.empty
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    def add(ni: NestedInteger) = {}
  }


  class NestedIterator(_nestedList: List[NestedInteger]) {
    val it = _nestedList.flatMap({
      case e if e.isInteger => List(e.getInteger)
      case e => flattern(e.getList.toList)  
    }).iterator

    def flattern: List[NestedInteger] => List[Int] = {
      case nl => nl.flatMap {
        case ni if ni.isInteger => List(ni.getInteger)
        case ni => flattern(ni.getList.toList)
      }
    }
    def next(): Int = {
      it.next
    }
    def hasNext(): Boolean = {
      it.hasNext
    }
  }
}