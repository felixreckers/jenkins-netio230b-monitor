package monitor

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.typesafe.config.ConfigFactory

/**
 * Checks that properties mentioned as a part of application.conf
 * are read properly.
 */
@RunWith(classOf[JUnitRunner])
class MonitorTestSuite extends FunSuite {

  test("all config values must exist") {
    val conf = ConfigFactory.load
    assert(true === conf.hasPath("monitor.jenkins.url"))
    assert(true === conf.hasPath("monitor.jenkins.jobNames"))
    assert(true === conf.hasPath("monitor.netio230b.user"))
    assert(true === conf.hasPath("monitor.netio230b.password"))
    assert(true === conf.hasPath("monitor.netio230b.url"))
  }
}

