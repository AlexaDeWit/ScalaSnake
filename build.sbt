organization := "com.github.alexadewit"
name := "scalaSnake"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.3"

val Http4sVersion = "0.18.0-M3"
val LogbackVersion = "1.2.3"
val circeVersion = "0.9.0-M1"

libraryDependencies ++= Seq(
  "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
  "io.circe"        %% "circe-parser"        % circeVersion,
  "io.circe"        %% "circe-generic"       % circeVersion
)
