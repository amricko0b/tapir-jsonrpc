import sbt._

object Dependencies {
  private val tapirV = "0.18.3"
  private val munitV = "0.7.29"

  val tapir: Seq[ModuleID] = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core"
  ).map(_ % tapirV)

  val munit: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit"
  ).map(_ % munitV).map(_ % Test)
}
