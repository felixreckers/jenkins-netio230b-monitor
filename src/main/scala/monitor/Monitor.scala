package monitor

import com.typesafe.config._
import scala.collection.JavaConversions._

/**
 * The start object for the monitor tool.
 */
object Monitor extends App {

  val conf = ConfigFactory.load
  val netioController = createNetioController()
  
  if (args.size > 0) {
    parseArgs(args)
  } else {
    val status = getStatusFromJenkins(conf)
    println("status=" + status)
    netioController.sendStatus(status)
  }

  def parseArgs(args: Array[String]) {
    if(args.size == 1 && args(0) == "-x") 
      netioController.sendStatus(NoLights)
    else throw new IllegalArgumentException("Usage: java -jar jenkins-traffic-light-monitor.jar [-x]")
  }
  
  def getStatusFromJenkins(conf: Config): Status = {
    val statusRetriever = new JenkinsJobStatusRetriever(conf.getString("monitor.jenkins.url"))
    val jobNames = conf.getStringList("monitor.jenkins.jobNames")
    statusRetriever.findStatusOfJobs(jobNames.toSet)
  }

  def createNetioController(): NetIo230bController = {
    val user = conf.getString("monitor.netio230b.user")
    val password = conf.getString("monitor.netio230b.password")
    val url = conf.getString("monitor.netio230b.url")
    new NetIo230bController(url, user, password)
  }
  
  
}