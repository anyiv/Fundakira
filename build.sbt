name := """Fundakira"""
organization := "com.prueba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.0"
libraryDependencies += jdbc
libraryDependencies += guice
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.7.2"

dependencyOverrides ++= Seq(
  "com.typesafe" % "ssl-config-core_2.13" % "0.3.8",
  "com.google.guava" % "guava" % "27.1-jre",
)

