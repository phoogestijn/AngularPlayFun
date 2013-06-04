import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "AngularPlayFun"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.webjars" % "angularjs" % "1.1.5-1",
    "org.webjars" % "requirejs" % "2.1.1",
    "org.webjars" % "webjars-play" % "2.1.0-1",
    "org.webjars" % "jasmine" % "1.3.1")

  val main = play.Project(
    appName, appVersion, appDependencies).settings(
      requireJs += "main.js",
      requireJsShim += "main.js")

}