
import java.lang.Exception
import scala.util.{Failure, Success, Try}
/**
  * Created by ilya on 11.11.2017.
  */
object TryTest {
  def main(args: Array[String]): Unit = {
    configure() match {
      case Success((name, version)) => println(s"os name: $name, os version: $version")
      case Failure(e) => println("failure: " + e)
    }
    configureFor() match {
      case Success((n, v)) => println(s"os name: $n, os version: $v")
      case Failure(e) => println("failure: " + e)
    }
    configureOpt() match {
      case Some((n, v)) => println(s"os name: $n, os version: $v")
      case _ => println("nothing")
    }
    configureOptFor() match {
      case Some((n, v)) => println(s"os name: $n, os version: $v")
      case _ => println("nothing")
    }
    configureOptFlatMap() match {
      case Some((n, v)) => println(s"os name: $n, os version: $v")
      case _ => println("nothing")
    }

  }

  def configure(): Try[(String, String)] = {
    val oName = System.getProperty("os.name")
    val oVersion = System.getProperty("os.version")
    Try {
      throw new Exception("don't wanna show name and version")
      (oName, oVersion)
    }
  }

  def configureFor(): Try[(String, String)] = {
    for {
      exc <- Try(throw new Exception("don't wanna show name and version"))
      oName <- Try(System.getProperty("os.name"))
      oVersion <- Try(System.getProperty("os.version"))
    }
      yield (oName, oVersion)
  }
   //3
  def configureOpt(): Option[(String, String)] = {
    val oName = Option(System.getProperty("os.name"))
    val oVersion = Option(System.getProperty("os.version"))
    (oName, oVersion) match {
      case (Some(n), Some(v)) => Some((n, v))
      case _ => None
    }
  }
  //4
  def configureOptFor(): Option[(String, String)] = {
    for {
      oName <- Option(System.getProperty("os.name"))
      oVersion <- Option(System.getProperty("os.version"))
    } yield (oName, oVersion)
  }
  //5. 3 eq. 4 eq. 5
  def configureOptFlatMap(): Option[(String, String)] = {
    Option(System.getProperty("os.name")).flatMap(n => Option(System.getProperty("os.version")).map(v=>(n,v)))
  }
}
