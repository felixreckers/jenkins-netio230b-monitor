package monitor

import scala.io.Source
import scala.util.parsing.json.JSON

/**
 * Queries Jenkins over the json-API for the status of the configured jobs. 
 * If at least one job is failed (color blue) the status will be Red. 
 * If at least one job is executing (blinking symbol) the status will be Yellow.
 * Else the status will be green.
 */
class JenkinsJobStatusRetriever(url: String) {

  type Job = Map[String, String]
  
  def findStatusOfJobs(jobNames: Set[String]): Status = {
    val jenkinsJsonInfo = Source.fromURL(url)
    JSON.parseFull(jenkinsJsonInfo.mkString) match {
      case Some(m: Map[String, Any]) => (m("jobs") match {
        case jobs: List[Job] => findWorstStatus(jobs, jobNames)
      })
      case _ => Yellow
    }
  }

  private def findWorstStatus(jobs: List[Job], names: Set[String]): Status = 
    (filterJobsByName(jobs, names) map jobToStatus).foldLeft (Green:Status) (_ + _)
  
  
  private def filterJobsByName(jobs: List[Job], names: Set[String]): List[Job] =
    jobs filter { names contains _("name") } 


  private def jobToStatus(job: Job): Status = job("color") match {
    case "blue" => Green
    case x:String if x.endsWith("_anime") => Yellow
    case _ => Red
  }
}