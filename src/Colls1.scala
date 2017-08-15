import scala.collection.mutable.Set
import scala.collection.JavaConverters._
object Colls1 {
  def main(args: Array[String]): Unit = {
 /*  val names:Set[String] = Set.empty[String]
   names+="ilya"
   names+="Victor"
    println(names)
    names-="Victor"
    println(names)
    names-="ilya"
    println(names)
*/
 /*   val names = List("Tom","Fred", "Harry", "Dick")
    val namesMappings = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
    println(intersectCollections(names,namesMappings))

    val lst = List (3,5,7,9)
    println((lst :\ List[Int]())(_ :: _) )
    println((List[Int]() /:lst)((x,y) =>y +: x))

    */

   /* val prices = List[Double](5.0, 20.0, 9.95)
    val quantities = List[Double](10, 2, 1)
    val f = ((x:Double,y:Double)=>x*y).tupled
      println((prices zip quantities).map(f))

      */

    java.util.TimeZone.getAvailableIDs.foreach(x=>println(s" "+x))

//    val entries = java.util.TimeZone.getAvailableIDs.filter(_.lastIndexOf('/') > -1).
//      groupBy(x=>x.substring(0,x.indexOf('/'))) map {case (x:String,y:Array[String])=>(x,y.length)}
//    println(entries.toSeq.sortWith(_._2 > _._2))
    //.keys.foreach(x=>println(s" "+x))
  }

  def intersectCollections[A,B](coll:Seq[A], m:Map[A,B]):Seq[B]={
    coll.flatMap(x=>m.map(y=> (x,y) match {
      case (a,b) if a==b._1 => Some(b._2)
      case _=> None
    })).filter(_.isDefined).map(_.get)
  }
}
