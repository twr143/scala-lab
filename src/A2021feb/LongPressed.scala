package A2021feb

/**
  * Created by twr143 on 07.02.2021 at 13:41.
  */
object LongPressed {
  def isLongPressedName(name: String, typed: String): Boolean = {
    if (name.length > typed.length) return false
    var j = 0
    for (i <- 0 to name.length - 1) {
      if (j >= typed.length || name(i) != typed(j)) return false
      while (i < name.length - 1 && name(i + 1) != name(i) && j < typed.length - 1 && typed(j) == typed(j + 1)) {
        j += 1
      }
      while (i == name.length - 1 && j < typed.length - 1 && typed(j) == typed(j + 1))
        j += 1
      j += 1
    }
    while (j < typed.length)
      if (name.last == typed(j)) j += 1 else return false
      true
  }

  def main(args: Array[String]): Unit = {
    println(isLongPressedName("pyplrz", "ppyypllr"))
  }
}
