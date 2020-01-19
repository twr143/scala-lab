package algo.design.autocomplete

/**
  * Created by Ilya Volynin on 19.01.2020 at 10:50.
  */
object AutoCompleteEntry {
  def main(args: Array[String]): Unit = {
    val sentences = Array("i love you","island","ironman","i love leetcode")
    val times = Array(5,3,2,2)
    val obj = new AutocompleteSystem(sentences, times)
    println(obj.input('i'))
    println(obj.input(' '))
    println(obj.input('a'))
    println(obj.input('#'))
    println(obj.showCurrent)
    println(obj.input('i'))
  }
}
