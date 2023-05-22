package A2022Jul

object Interpol {
  val data =
    """   <iin>@IIN@</iin>
      |                        <docNum>@docNum@</docNum>
      |                        <docIssueDate>@docIssueDate@</docIssueDate>""".stripMargin

  val s1 = s"""
         |<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
         |   <soapenv:Header/>
         |    <soapenv:Body>
         |        <ns1:SendMessage xmlns:ns1="http://bip.bee.kz/SyncChannel/v10/Types">
         |            <request>
         |                <requestInfo>
         |                    <messageId>@guid@</messageId>
         |                    <serviceId>@penaltiesServiceCode@</serviceId>
         |                    <messageDate>@messageDate@</messageDate>
         |                    <sender>
         |                        <senderId>@senderId@</senderId>
         |                        <password>@password@</password>
         |                    </sender>
         |                </requestInfo>
         |                <requestData>
         |                    <data>
         |                     $data
         |                    </data>
         |                </requestData>
         |            </request>
         |        </ns1:SendMessage>
         |    </soapenv:Body>
         |</soapenv:Envelope>
         |""".stripMargin



  def main(args: Array[String]): Unit = {
     println(s1)
  }

}
