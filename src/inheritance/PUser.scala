package inheritance
import java.time.ZonedDateTime
import java.util.UUID
/**
  * Created by Ilya Volynin on 21.04.2018 8:46.
  */
object PUser {
  def main(args: Array[String]): Unit = {
    val pu2 = new PartnerUser2()
    println(pu2)
  }
  trait User {
    def id: UUID

    def partnerId: UUID
  }
  case class PartnerUser1(id: UUID, partnerId: UUID) extends User
  class PartnerUser2() extends User {
    def id: UUID = UUID.randomUUID()
    def partnerId: UUID = UUID.randomUUID()
    override def toString = s"$id $partnerId"
  }
}
