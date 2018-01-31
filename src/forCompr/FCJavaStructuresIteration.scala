package forCompr

/*
created by Ilya Volynin at 31.01.18
*/
object FCJavaStructuresIteration {
  def main(args: Array[String]): Unit = {
        for ((k, v) <- scala.collection.JavaConverters.dictionaryAsScalaMap(System.getProperties).retain((k, v) => !v.toString.isEmpty))
          println(s"$k -> $v")

  }
}
