package com.concurrency.examples

import java.util.concurrent.{ExecutorService, Executors}

import org.scalatest.FunSuite

class ExponentialBackoffDemoTest extends FunSuite {
  test("retry 5 times without real exponential backoff - offset set to 1") {
    val accountDetails = new AccountDetails

    val executor : ExecutorService = Executors.newFixedThreadPool(2)
    executor.execute(accountDetails.calculateSaving)
    executor.execute(accountDetails.calculateIncome)
    assertThrows[UnsupportedOperationException](
      ExponentialBackoffDemo.retry(5, 800, 1)(accountDetails.getSavingPercentage()))
    executor.shutdown()
  }
  test("retry 5 times with exponential backoff - offset set to 2") {
    val accountDetails = new AccountDetails

    val executor : ExecutorService = Executors.newFixedThreadPool(2)
    executor.execute(accountDetails.calculateSaving)
    executor.execute(accountDetails.calculateIncome)
    assert(ExponentialBackoffDemo.retry(5, 800, 2)(accountDetails.getSavingPercentage()) == 40.0)
    executor.shutdown()
  }
}
