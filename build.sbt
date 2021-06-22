name := "Practice-Scala"
version := "1.0"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "3.0.8" % "test",
	"com.typesafe.scala-logging" %% "scala-logging" % "3.9.3",
	"ch.qos.logback" % "logback-classic" % "1.1.2"
)

/* MongoDB connection */
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.1.1"

/* AtomicDouble */
libraryDependencies += "com.google.guava" % "guava" % "20.0"

scalacOptions := Seq("-unchecked", "-deprecation")
