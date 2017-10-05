import java.net.IDN

/*
created by Ilya Volynin at 08.09.17
*/
object I18N {
  def main(args: Array[String]): Unit = {
    val address = "илья@россия.рф"
    val addressEn = "ilya@russia.ru"
    val encodedAddress = IDN.toASCII(address)
    val unencodedAddress = IDN.toUnicode(encodedAddress)
    println(encodedAddress)
    println(unencodedAddress)

    println(IDN.toASCII(addressEn))

  }
}
