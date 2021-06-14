package com.concurrency.examples

import java.util.concurrent.{ExecutorService, Executors, Future, TimeUnit, TimeoutException}

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FunSuite
import org.scalatest.Matchers.{noException, thrownBy}

/**
 * Created by Saurav Sahu on 14th June 2021
 */
class WaitingNonBlockingWayTest extends FunSuite with LazyLogging {
  val executorService : ExecutorService = Executors.newSingleThreadExecutor()
  val future : Future[String] = executorService.submit({ () =>
    Thread.sleep(4000)
    "Done"})
  test("specify timeout for a given operation - wait for less time than execution time - TimeoutException thrown"){
    assertThrows[TimeoutException](future.get(2000, TimeUnit.MILLISECONDS)) // wait for less time
  }
  test("specify timeout for a given operation - wait for sufficient time - No Exception thrown"){
    noException shouldBe thrownBy(future.get(5000, TimeUnit.MILLISECONDS))
  }
  test("non-blocking way of waiting - using onComplete - scala.concurrent.Future") {
    WaitingNonBlockingWay.performAsyncWait
    assert(WaitingNonBlockingWay.recordProgress.get() == WaitingNonBlockingWay.WaitingMsg)
    // wait little more than sleep time to let task finish
    Thread.sleep(WaitingNonBlockingWay.waitingTime+2000)

    assert(WaitingNonBlockingWay.recordProgress.get() ==
      WaitingNonBlockingWay.WaitingMsg + WaitingNonBlockingWay.SuccessRcvdMsg)
  }
}
