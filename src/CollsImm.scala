  object CollsImm {
    def main(args: Array[String]): Unit = {
      var names: Set[String] = Set.empty[String]
      names += "ilya"
      names += "Victor"
      println(names)
      names -= "Victor"
      println(names)
      names -= "ilya"
      println(names)
      println()


      var namesAge: Map[String,Int] = Map.empty[String,Int]
      namesAge+="ilya"->35
      namesAge+="Victor"->39
      println(namesAge)
      val contains = namesAge.contains("ilya")
      println(contains)
      namesAge-="ilya"
      println(namesAge)

    }
  }
