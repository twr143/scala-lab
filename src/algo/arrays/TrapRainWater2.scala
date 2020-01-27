package algo.arrays

/**
  * Created by Ilya Volynin on 27.01.2020 at 10:02.
  */
object TrapRainWater2 {
  def trapRainWater(heightMap: Array[Array[Int]]): Int = {
    val h = heightMap
    if (h.isEmpty) return 0
    val m = h.length
    val n = h(0).length
    val rowMaxInd = Array.fill(m)(0)
    val colMaxInd = Array.fill(n)(0)
    var V = 0
    val locRowHeights = Array.ofDim[Int](m)
    val locColHeights = Array.ofDim[Int](n)
    for (i <- 0 to m - 1) {
      var max = 0
      for (j <- 0 to n - 1)
        if (h(i)(j) > max) {
          max = h(i)(j)
          rowMaxInd(i) = j
        }
    }
    for (j <- 0 to n - 1) {
      var max = 0
      for (i <- 0 to m - 1)
        if (h(i)(j) > max) {
          max = h(i)(j)
          colMaxInd(j) = i
        }
    }
    for (i <- 0 to m - 1)
      locRowHeights(i) = h(i)(0)

    for (j <- 0 to n - 1)
      locColHeights(j) = h(0)(j)

    for (i <- 1 to m - 2) // 1st quadrant
      for (j <- 1 to n - 2)
        if (i < colMaxInd(j) && j < rowMaxInd(i)) {
          val min = math.min(locRowHeights(i), locColHeights(j))
          if (h(i)(j) <= min) V += (min - h(i)(j))
          else if (locRowHeights(i) < h(i)(j)) locRowHeights(i) = h(i)(j)
          else if (locColHeights(j) < h(i)(j)) locColHeights(j) = h(i)(j)
        }

    for (i <- 0 to m - 1)
      locRowHeights(i) = h(i)(n - 1)

    for (i <- 1 to m - 2) // 2nd quadrant
      for (j <- n - 2 to 1 by -1)
        if (i < colMaxInd(j) && j > rowMaxInd(i)) {
          val min = math.min(locRowHeights(i), locColHeights(j))
          if (h(i)(j) <= min) V += (min - h(i)(j))
          else if (locRowHeights(i) < h(i)(j)) locRowHeights(i) = h(i)(j)
          else if (locColHeights(j) < h(i)(j)) locColHeights(j) = h(i)(j)
        }
    for (i <- 0 to m - 1)
      locRowHeights(i) = h(i)(0)

    for (j <- 0 to n - 1)
      locColHeights(j) = h(m - 1)(j)

    for (i <- m - 2 to 1 by -1) // 3rd quadrant
      for (j <- 1 to n - 2)
        if (i > colMaxInd(j) && j < rowMaxInd(i)) {
          val min = math.min(locRowHeights(i), locColHeights(j))
          if (h(i)(j) <= min) V += (min - h(i)(j))
          else if (locRowHeights(i) < h(i)(j)) locRowHeights(i) = h(i)(j)
          else if (locColHeights(j) < h(i)(j)) locColHeights(j) = h(i)(j)
        }
    for (i <- 0 to m - 1)
      locRowHeights(i) = h(i)(n - 1)

    for (i <- m - 2 to 1 by -1) // 4th quadrant
      for (j <- n - 2 to 1 by -1)
        if (i > colMaxInd(j) && j > rowMaxInd(i)) {
          val min = math.min(locRowHeights(i), locColHeights(j))
          if (h(i)(j) <= min) V += (min - h(i)(j))
          else if (locRowHeights(i) < h(i)(j)) locRowHeights(i) = h(i)(j)
          else if (locColHeights(j) < h(i)(j)) locColHeights(j) = h(i)(j)
        }
    V
  }

  def main(args: Array[String]): Unit = {
   println(trapRainWater(Array(Array(5,5,5,1),
     Array(5,1,1,5),Array(5,1,5,5),
     Array(5,2,5,8))))
  }
}
