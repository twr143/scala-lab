package A2021Apr

import scala.collection.mutable._

/**
  * Created by twr143 on 30.03.2021 at 12:23.
  */
class AuthenticationManager(_timeToLive: Int) {

  implicit def ord(m: Map[String, Int]) = Ordering.fromLessThan[String]((f, s) => {
    val tf = m(f)
    val ts = m(s)
    if (ts != tf) tf < ts else f < s
  })

  val tokenTime = Map.empty[String, Int]
  var tokens = TreeSet.empty[String](ord(tokenTime))

  def generate(tokenId: String, currentTime: Int) {
    tokenTime += (tokenId -> (currentTime + _timeToLive))
    tokens += tokenId
  }

  def renew(tokenId: String, currentTime: Int) {
    tokens = tokens.dropWhile(tokenTime(_) <= currentTime)
    if (tokenTime.contains(tokenId) && tokens.contains(tokenId))
      tokenTime(tokenId) = currentTime + _timeToLive
  }

  def countUnexpiredTokens(currentTime: Int): Int = {
    tokens = tokens.dropWhile(tokenTime(_) <= currentTime)
    tokens.size
  }


}
