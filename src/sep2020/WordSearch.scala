package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 24.09.2020 at 9:41.
  */
object WordSearch {

  def backtrack(board: Array[Array[Char]], word: String, ind: Int, i: Int, j: Int): Boolean = {
    if (ind == word.length) true
    else if (i < 0 || j < 0 || i >= board.length || j >= board(0).length) false
    else {
      val temp = board(i)(j)
      board(i)(j) = '*'
      val res =
        (i > 0 && board(i - 1)(j) == word(ind)
          && backtrack(board, word, ind + 1, i - 1, j)) ||
        (i < board.length - 1 && board(i + 1)(j) == word(ind)
          && backtrack(board, word, ind + 1, i + 1, j)) ||
        (j > 0 && board(i)(j - 1) == word(ind)
          && backtrack(board, word, ind + 1, i, j - 1)) ||
        (j < board(0).length - 1 && board(i)(j + 1) == word(ind)
          && backtrack(board, word, ind + 1, i, j + 1))
      board(i)(j) = temp
      res
    }
  }

  def exist(board: Array[Array[Char]], word: String): Boolean = {
    for (i <- 0 to board.length - 1)
      for (j <- 0 to board(0).length - 1)
        if (board(i)(j) == word(0))
          if (backtrack(board, word, 1, i, j)) return true
    false
  }


  def main(args: Array[String]): Unit = {
    val b = Array(Array('A', 'B', 'C', 'E'), Array('S', 'F', 'E', 'S'), Array('A', 'D', 'E', 'E'))
    println(exist(b, "ABCESEEEFS"))
    //    println(exist(b, "SEE"))
        println(exist(b, "ABCB"))
    //    val b2 = Array(Array('A', 'A'))
    //    println(exist(b2, "AA"))

  }
}
