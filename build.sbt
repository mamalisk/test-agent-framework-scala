
organization := "net.masterthought"

name := "taf-scala"

version := "0.0.1"

scalaVersion := "2.10.1"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0.M5b" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test",
  "org.seleniumhq.selenium" %% "selenium-java" % "2.31.0"
)

parallelExecution in Test := true

retrieveManaged := true

transitiveClassifiers := Seq("sources")

initialCommands in console := "import net.masterthought._"

//initialCommands in (Test, console) <<= (initialCommands in console)(_ + ",TestData._")

//scalariformSettings


resolvers += "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository"
