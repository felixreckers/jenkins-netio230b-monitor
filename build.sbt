
name := "jenkins-netio230b-monitor"
 
version := "1.1"

scalaVersion := "2.9.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.skife.com.typesafe.config" % "typesafe-config" % "0.3.0"

Seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)