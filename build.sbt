ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.5"

lazy val root = (project in file(".")).settings(
  name := "cats-effect-3-quick-start",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "2.5.4",
    // better monadic for compiler plugin as suggested by documentation
    compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    "org.http4s" %% "http4s-dsl" % "0.21.22",
    "org.http4s" %% "http4s-blaze-server" % "0.21.22",
    "org.http4s" %% "http4s-circe" % "0.21.22",
    "io.circe" %% "circe-generic" % "0.13.0",
    "io.circe" %% "circe-parser" % "0.13.0",
    "co.fs2" %% "fs2-core" % "2.5.9",
    "io.estatico" %% "newtype" % "0.4.4"
  )
)
