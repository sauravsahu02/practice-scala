package com.concurrency.examples

import java.util.concurrent.{CountDownLatch, Executors, TimeUnit}

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu on 14th June 2021
 */
class CountDownLatchDemoTest extends FunSuite {
  test ("check latch count is decremented each time - using await on CountDownLatch") {
    //  One thread waits for n threads, specified while creating the CountDownLatch
    val nTasks = 4
    val latch = new CountDownLatch(nTasks)
    val executor = Executors.newFixedThreadPool(nTasks)
    assert(latch.getCount == nTasks)
    for (i <- 1 to nTasks) {
      executor.execute(CountDownLatchDemo(latch, makeWaitIndefinite = false))
    }
    executor.shutdown()
    latch.await() // A dependent thread calling await() will block until the worker threads are finished.
    assert(latch.getCount == 0)
  }
  test ("continue application rather than indefinite block") {
    val nTasks = 4
    val latch = new CountDownLatch(nTasks)
    val executor = Executors.newFixedThreadPool(nTasks)
    assert(latch.getCount == nTasks)
    for (i <- 1 to nTasks) {
      executor.execute(CountDownLatchDemo(latch, makeWaitIndefinite = true))
    }
    executor.shutdown()
    val completed = latch.await(5000, TimeUnit.MILLISECONDS)  // Add timeout to prevent indefinite waiting by application
    assert(latch.getCount == 4)
    assert(!completed)
  }
}
