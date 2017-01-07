
name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaCore,
  filters
)

// https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.6.7"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype OSS Rekeases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Apache" at "http://repo1.maven.org/maven2"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

