/*
 * Created by Saurav Sahu on 27/06/21, 1:52 PM.
 */

package com.functional.programming.exercises

import scala.concurrent.blocking
import scala.io.Source

object WorldAnnualPopulationDataAggregator {  // uses lazy val to avoid recalculation
  lazy val worldPopulation2020 = {
    Thread.sleep(2000)
    123456789
  }
  lazy val worldPopulation2021 = {
    Thread.sleep(2000)
    123496785
  }
}
object WorldAnnualPopulationFinder {
  val worldPopulation2020 = {
    blocking(Thread.sleep(2000))
    123456789
  }
  val worldPopulation2021 = {
    blocking(Thread.sleep(2000))
    123496785
  }
}
object EvaluateLazyVal {
  val pathname = "/tmp/newFile.txt"
  lazy val lazyReaderFileLines = {
    val source = Source.fromFile("/tmp/newFile.txt")
    try source.getLines() finally source.close()
  }
}
object EvaluateNormalVal {
  val normalReaderFileLines = {
    val source = Source.fromFile("/tmp/newFile.txt")
    try source.getLines() finally source.close()
  }
}