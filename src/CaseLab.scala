object CaseLab {
  abstract class Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification{
    override def toString:String= s"contact: $contactName link: $link"
  }
  case class TelegramMessage(contactName: String, msg: String) extends Notification{
    override def toString:String= s"contact: $contactName msg: $msg"
  }

  def showNotification(n:Notification):String={
    n match {
      case Email(sender, title, body) =>
       "you've got email from "+sender +" called "+title
      case SMS(caller , message) =>
        "you've got an SMS from "+caller+" with message "+message
      case v@VoiceRecording(_,_) => v.toString
      case a@any=> s"[Notif. not implemented] $a"
    }
  }
  def main(args: Array[String]): Unit = {
    val someVoiceRecording = VoiceRecording("Ilya", "voicerecording.org/id/123")
    val someEmail = Email("Me","title" ,"voicerecording.org/id/123")
    val telegramMessage = TelegramMessage("Me","some mock message" )
//    println(showNotification(someVoiceRecording))
    println(showNotification(someEmail))
    println(showNotification(someVoiceRecording))
    println(showNotification(telegramMessage))
  }
}
