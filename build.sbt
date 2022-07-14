import Dependencies._

lazy val possum = (project in file("."))
  .aggregate(core)
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
    libraryDependencies ++= tapir ++ munit,
  )