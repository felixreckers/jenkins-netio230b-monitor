package monitor

import java.net.URL
import scala.io.Source
import java.net.SocketException

/**
 * Sends HTTP GET commands to the Netio 230b. 
 */
class NetIo230bController(url: String, user: String, password: String, 
    val connnectionTimeout: Int = 2000, val readTimeout: Int = 2000) {

  def sendStatus(status: Status) = {
    sendHttpRequest("login=p:" + user.trim + ":" + password.trim + "&port=" + status.toCode)
  }

  private def sendHttpRequest(command: String) = {
    try {
      val url = new URL(this.url + command)
      println("url="+url)
      val connection = url.openConnection
      connection.setConnectTimeout(connnectionTimeout)
      connection.setReadTimeout(readTimeout)
      val reply = Source.fromInputStream(connection.getInputStream).mkString
      println(reply)
    } catch {
      case e: Exception => println(e)
    }
  }
}