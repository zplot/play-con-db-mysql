name := """play-getting-started"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  ws,
  "mysql" % "mysql-connector-java" % "5.1.41"
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
