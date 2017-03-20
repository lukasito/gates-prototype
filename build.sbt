name := "gates_prototype"

version := "1.0"

lazy val `gates_prototype` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(jdbc, cache, javaWs, specs2 % Test)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
