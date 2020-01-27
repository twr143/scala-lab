package algo.arrays

/**
  * Created by Ilya Volynin on 26.01.2020 at 13:17.
  */
object TrapRainWater {
  def trap(height: Array[Int]): Int = {
    if (height.length==0) return 0
    var iMax, V, hMax = 0
    var max = height(0)
    for (i <- 0 to height.length - 1) {
      if (height(i) > max) {
        max = height(i)
        iMax = i
      }
    }
    for (i <- 0 to iMax - 1) {
      if (height(i) > height(hMax)) hMax = i
      else if (height(i) < height(hMax)) V += height(hMax) - height(i)
    }
    hMax = height.length - 1
    for (i <- height.length - 1 to iMax + 1 by -1) {
      if (height(i) > height(hMax)) hMax = i
      else if (height(i) < height(hMax)) V += height(hMax) - height(i)
    }
    V
  }

  def main(args: Array[String]): Unit = {
    println(trap(Array(0,1,0,2,1,0,1,3,2,1,2,1)))
  }
}
