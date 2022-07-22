import sbt._

object Dependencies {
  val tapirV = "0.18.3"
  val munitV = "0.7.29"

  val munit: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit"
  ).map(_ % munitV).map(_ % Test)
}
