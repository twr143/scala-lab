
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
    configureOpt() match {
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

  def configureOpt(): Option[(String, String)] = {
    val oName = Option(System.getProperty("os.name"))
    val oVersion = Option(System.getProperty("os.version"))
    (oName, oVersion) match {
      case (Some(n), Some(v)) => Some((n, v))
      case _ => None
    }
  }
}
