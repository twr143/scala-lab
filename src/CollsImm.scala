import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object CollsImm {
    def main(args: Array[String]): Unit = {
     /* var names: Set[String] = Set.empty[String]
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
*/


      val seq= Seq("firstname" -> "ilya")++
      Seq("lastname" -> "vol&ynin")

      val map = seq.toMap

      println(seq)
      println(map)

      /*val map2=map.mapValues(Seq(_))
      println(map2)
      val seq2= map2.toSeq
      println(seq2)

      val seq3 = seq2.flatMap { kv =>
        kv._2.map(kv._1 -> _)
      }
      println(seq3)
      val seq4 = seq3.map { kv =>
        s"${kv._1}=${URLEncoder.encode(kv._2, StandardCharsets.UTF_8.toString)}"
      }

        println(seq4)
        val seq5 = seq4.mkString("&")
        println(seq5)
*/

        val map5 = map.mapValues(URLEncoder.encode(_, StandardCharsets.UTF_8.toString))
        println(map5)
        val seq6 = map5.map{kv⇒s"${kv._1}=${kv._2}"}
        println(seq6)
        val map6 = seq6.mkString("&")
        println(map6)
    }
  }
