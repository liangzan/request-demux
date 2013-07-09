import AssemblyKeys._

assemblySettings

organization := "com.sproutmode"

name := "request-demux"

version := "0.0.1"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.6.8",
  "net.databinder" %% "unfiltered-jetty" % "0.6.8",
  "net.databinder.dispatch" %% "dispatch-core" % "0.10.1",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
  "com.typesafe" % "config" % "1.0.2",
  "net.databinder" %% "unfiltered-spec" % "0.6.8" % "test"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)

initialize ~= { _ =>
  System.setProperty("org.slf4j.simpleLogger.logFile", "log/demux.log")
}

jarName in assembly := "demux.jar"

test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "about.html"     => MergeStrategy.discard
    case x => old(x)
  }
}
