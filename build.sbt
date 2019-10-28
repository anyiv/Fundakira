name := """Fundakira"""
organization := "com.prueba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.0"
libraryDependencies += jdbc
libraryDependencies += guice
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.6"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"
libraryDependencies += evolutions
libraryDependencies += javaForms
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.0-RC3"

dependencyOverrides ++= Seq(
  "com.typesafe" % "ssl-config-core_2.13" % "0.3.8",
  "com.google.guava" % "guava" % "27.1-jre",
)

lazy val nonEnhancedProject = (project in file("non-enhanced"))
  .disablePlugins(PlayEnhancer)