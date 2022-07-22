import Dependencies._

ThisBuild / scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)

lazy val possum = (project in file("."))
  .aggregate(core, circe)
  .settings(
    organization := "xyz.amricko0b",
    name := "tapir-jsonrpc",
    version := "0.0.1",
    scalaVersion := "2.13.8",
    testFrameworks += new TestFramework("munit.Framework")
  )

lazy val core = (project in file("core"))
  .settings(
    name := "tapir-jsonrpc-core",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirV
    ) ++ munit
  )

lazy val circe = (project in file("circe"))
  .dependsOn(core)
  .settings(
    name := "tapir-jsonrpc-circe",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirV
    ) ++ munit
  )
