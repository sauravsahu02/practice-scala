/*
 * Created by Saurav Sahu on 27/06/21, 1:52 PM.
 */

package com.functional.programming.exercises

import java.io.{File, FileNotFoundException, PrintWriter}

import org.scalatest.FunSuite
import org.scalatest.Matchers.{noException, thrownBy}

class EvaluateLazyValTest extends FunSuite {
  test("low definition cost and high first-time access cost - 'lazy val'"){
    var t = System.currentTimeMillis()
    val aggregator = WorldAnnualPopulationDataAggregator
    // cheap definition time
    assert(System.currentTimeMillis() - t < 10, s"Time taken when the object is defined : ${System.currentTimeMillis() - t}")
    t = System.currentTimeMillis()
    // costly first-time access
    assert(aggregator.worldPopulation2020 == 123456789)
    assert(System.currentTimeMillis() - t >= 2000, s"Time taken - accessing one 'lazy val'ue first time: ${System.currentTimeMillis() - t}")
    t = System.currentTimeMillis()
    assert(aggregator.worldPopulation2021 == 123496785)
    assert(System.currentTimeMillis() - t >= 2000, s"Time taken - accessing another 'lazy val'ue first time: ${System.currentTimeMillis() - t}")
    t = System.currentTimeMillis()
    assert(aggregator.worldPopulation2020 == 123456789)
    // normal access time
    assert(System.currentTimeMillis() - t < 10, s"Time taken - accessing one 'lazy val'ue second time: ${System.currentTimeMillis() - t}")
  }
  test("expensive definition time and normal access cost all the way - simply 'val'") {
    var t = System.currentTimeMillis()
    val finder = WorldAnnualPopulationFinder
    // costly definition time - sum of time taken in executing ALL 'val', but what if, say, we're interested in year 2021 data only.
    assert(System.currentTimeMillis() - t >= 4000, s"Time taken when the object is defined : ${System.currentTimeMillis() - t}")
    t = System.currentTimeMillis()
    assert(finder.worldPopulation2020 == 123456789)
    // normal access time from first time itself
    assert(System.currentTimeMillis() - t < 10, s"Time taken - accessing 'val' first time : ${System.currentTimeMillis() - t}")
    t = System.currentTimeMillis()
    assert(finder.worldPopulation2021 == 123496785)
    assert(System.currentTimeMillis() - t < 10, s"Time taken - accessing another 'val' first time: ${System.currentTimeMillis() - t}")
  }
  test("'lazy val' retries if never succeeded before") {
    new File(EvaluateLazyVal.pathname).delete()
    noException shouldBe thrownBy(EvaluateLazyVal)
    assertThrows[FileNotFoundException](EvaluateLazyVal.lazyReaderFileLines)
    val writer = new PrintWriter(new File(EvaluateLazyVal.pathname))
    writer.write("This is for a demo")
    writer.close()
    // lazyReaderFileLines retries to initialize and succeeds this time
    noException shouldBe thrownBy(EvaluateLazyVal.lazyReaderFileLines)
    new File(EvaluateLazyVal.pathname).delete()
  }
  test("with 'val', object creation itself fails promptly") {
    assertThrows[ExceptionInInitializerError](EvaluateNormalVal)
    assertThrows[NoClassDefFoundError](EvaluateNormalVal.normalReaderFileLines)
  }
}
