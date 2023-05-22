package A2021Nov

object LogoUpdate {

  def updatedLogo(oldLogo: String, newLogo: String): String = {
    if (oldLogo.isEmpty) newLogo
    else {
      val pointIndex = oldLogo.indexOf(".")
      val fileName = oldLogo.substring(0, pointIndex)
      val fileExt = oldLogo.substring(pointIndex + 1, oldLogo.length)
      println(s"filename = $fileName")
      println(s"fileext = $fileExt")
      if (fileName.equals(fileName.toUpperCase)
        && fileExt.equalsIgnoreCase("png")
      && !fileName.matches("^(CEA|FOND|MTC).*"))
        newLogo
      else oldLogo
    }
  }

  def main(args: Array[String]): Unit = {
    println(updatedLogo("TELECOM_SERV.png", "mtc.png"))
    println(updatedLogo("TELECOM_SERV.jpg", "mtc.png"))
    println(updatedLogo("CEADFDF.png", "BOOKM.png"))
    println(updatedLogo("FONDFDF.png", "BOOKM.png"))
    println(updatedLogo("FOFDF.png", "BOOKM.png"))
    println(updatedLogo("MTC_123.png", "BOOKM.png"))
    println(updatedLogo("1MTC_123.png", "BOOKM.png"))

  }

}
