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
    var V = 0
    val wL = Array.ofDim[Int](m, n)
    println("given:\n" + h.map(_.mkString(" ")).mkString("\n") + "\n")

    for (i <- 0 to m - 1)
      for (j <- 0 to n - 1)
        wL(i)(j) = if (i > 0 && j > 0 && i < m - 1 & j < n - 1) Int.MaxValue - 1 else h(i)(j)
//    println("0th:\n" + wL.map(_.mkString(" ")).mkString("\n") + "\n")

    for (i <- 1 to m - 2)
      for (j <- 1 to n - 2)
        if (h(i)(j) < wL(i - 1)(j) && h(i)(j) < wL(i)(j - 1))
          wL(i)(j) = math.min(wL(i)(j), math.min(wL(i - 1)(j), wL(i)(j - 1)))
        else wL(i)(j) = h(i)(j)

//    println("1st:\n" + wL.map(_.mkString(" ")).mkString("\n") + "\n")
    for (j <- n - 2 to 1 by -1)
      for (i <- 1 to m - 2)
        if (h(i)(j) < wL(i - 1)(j) && h(i)(j) < wL(i)(j + 1))
          wL(i)(j) = math.min(wL(i)(j), math.min(wL(i - 1)(j), wL(i)(j + 1)))
        else wL(i)(j) = h(i)(j)
    println("2nd:\n" + wL.map(_.mkString(" ")).mkString("\n") + "\n")

    for (i <- m - 2 to 1 by -1)
      for (j <- n - 2 to 1 by -1)
        if (h(i)(j) < wL(i + 1)(j) && h(i)(j) < wL(i)(j + 1))
          wL(i)(j) = math.min(wL(i)(j), math.min(wL(i + 1)(j), wL(i)(j + 1)))
        else wL(i)(j) = h(i)(j)

    println("3rd:\n" + wL.map(_.mkString(" ")).mkString("\n") + "\n")
    for (j <- 1 to n - 2)
      for (i <- m - 2 to 1 by -1)
        if (h(i)(j) < wL(i + 1)(j) && h(i)(j) < wL(i)(j - 1))
          wL(i)(j) = math.min(wL(i)(j), math.min(wL(i + 1)(j), wL(i)(j - 1)))
        else wL(i)(j) = h(i)(j)

    println("4th:\n" + wL.map(_.mkString(" ")).mkString("\n") + "\n")

    for (i <- 1 to m - 2)
      for (j <- 1 to n - 2)
        if (wL(i)(j) < Int.MaxValue)
          V += (wL(i)(j) - h(i)(j))
    //    println(wL.map(_.mkString(" ")).mkString("\n"))
    V
  }
  def main(args: Array[String]): Unit = {
//    println(trapRainWater(Array(Array(5,5,5,1),
//      Array(5,1,1,5), Array(5,1,5,5), Array(5,2,5,8))))
//    println(trapRainWater(Array(Array(5,8,7,7),
//      Array(5,2,1,5), Array(7,1,7,1), Array(8,9,6,9),Array(9,8,9,9))))
    println(trapRainWater(Array(Array(18776,12404,14443,15763,14613,14538,18606,16840,12904,14818),
      Array(15128,688,7369,7917,9917,6996,3324,7743,9470,2183),
      Array(18490,5499,9772,6725,5644,5590,7505,8139,2954,9786),
      Array(17669,8082,8542,8464,197,9507,9355,8804,6348,8611),
      Array(13622,7828,9299,7343,5746,5568,4340,5422,3311,3810),
      Array(17605,1801,5661,3730,4878,1305,9320,8736,9444,8626),
      Array(18522,3465,6708,3416,8282,3258,2924,7637,2062,5624),
      Array(12600,2036,3452,1899,9379,5550,7468,71,973,7131),
      Array(13881,4930,8933,5894,8660,163,7199,7981,8899,2996),
      Array(12959,3773,2813,9668,7190,1095,2926,6466,5084,1340))))
  }
}
