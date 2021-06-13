package com.concurrency.examples

import java.util.concurrent._
import java.util.concurrent.atomic.AtomicInteger

import com.concurrency.examples.BlockingQueueDemo._
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.Matchers.{noException, thrownBy}
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by Saurav Sahu on 11th June 2021
 *
 * Executor just executes stuff you give it.
 * ExecutorService adds startup, shutdown, and the ability to wait for and look at the status of jobs
 * Executor and ExecutorService‘s methods are scheduled on the spot without introducing any artificial delay
 *
 */
class BlockingQueueDemoTest extends FunSuite with BeforeAndAfter with LazyLogging {
  before{
    BlockingQueueDemo.runCount.set(0)
  }
  test("using SynchronousQueue - execute with sufficient pool size") {
    def executeWithEnoughPoolSize = {
      val executorService : ExecutorService = new ThreadPoolExecutor(1, 2, 0L,
        TimeUnit.MILLISECONDS, new SynchronousQueue[Runnable]())
      executorService.execute(SlowProcess("First"))
      executorService.execute(SlowProcess("Second"))
      executorService.shutdown()
      executorService.awaitTermination(1, TimeUnit.HOURS)
    }
    noException shouldBe thrownBy(executeWithEnoughPoolSize)
    assert(BlockingQueueDemo.runCount.get() == 2)
  }
  test("using SynchronousQueue - execute with insufficient pool-size - leading to exception") {
    def executeWithInsufficientPoolSize: Unit = {
      val executorService : ExecutorService = new ThreadPoolExecutor(1, 2, 0L,
        TimeUnit.MILLISECONDS, new SynchronousQueue[Runnable]())
      executorService.execute(SlowProcess("First"))
      executorService.execute(SlowProcess("Second"))
      executorService.execute(SlowProcess("Third"))
      executorService.shutdown()
    }
    assertThrows[RejectedExecutionException](executeWithInsufficientPoolSize)
    assert(BlockingQueueDemo.runCount.get() == 0)
  }
  test("handle insufficient pool-size - backpressure handling - using ArrayBlockingQueue") {
    def executeWithInsufficientPoolSize = {
      val executorService : ExecutorService = new ThreadPoolExecutor(2, 2, 1L,
        TimeUnit.HOURS, new ThreadPoolQueue("WaitsUntilThereIsSpace"))
      // automatically waits until there is space in the queue for the new task.
      executorService.execute(SlowProcess("first task using ArrayBlockingQueue"))
      executorService.execute(SlowProcess("second task using ArrayBlockingQueue"))
      executorService.execute(SlowProcess("third task using ArrayBlockingQueue"))
      executorService.shutdown()
      executorService.awaitTermination(1, TimeUnit.HOURS)
    }
    noException shouldBe thrownBy(executeWithInsufficientPoolSize)
    assert(BlockingQueueDemo.runCount.get() == 3)
  }
  test("sweet deal! no need to define a separate Thread Pool - just use FixedThreadPool"){
    def executeUsingFixedThreadPool = {
      val executorService: ExecutorService = Executors.newFixedThreadPool(10)
      // automatically waits until a thread is available in the queue for the new task.
      executorService.execute(SlowProcess("first task using FixedThreadPool"))
      executorService.execute(SlowProcess("second task using FixedThreadPool"))
      executorService.execute(SlowProcess("third task using FixedThreadPool"))
      executorService.shutdown()
      executorService.awaitTermination(1, TimeUnit.HOURS)
    }
    noException shouldBe thrownBy(executeUsingFixedThreadPool)
    assert(BlockingQueueDemo.runCount.get() == 3)
  }
  test("submit task - using single thread with unbounded queue - use SingleFixedThreadPool"){
    def executeUsingSingleFixedThreadPool = {
      val executorService: ExecutorService = Executors.newSingleThreadExecutor()
      // automatically waits until a thread is available in the queue for the new task.
      executorService.submit(SlowProcess("first task using SingleFixedThreadPool"))
      executorService.execute(SlowProcess("second task using SingleFixedThreadPool"))
      executorService.submit(SlowProcess("third task using SingleFixedThreadPool"))
      executorService.shutdown()
      executorService.awaitTermination(1, TimeUnit.HOURS)
    }
    noException shouldBe thrownBy(executeUsingSingleFixedThreadPool)
    assert(BlockingQueueDemo.runCount.get() == 3)
  }
  test ("run task per second - using scheduleAtFixedRate") {
    val runCount = new AtomicInteger()
    val executorService = Executors.newSingleThreadScheduledExecutor()
      .scheduleAtFixedRate(() => {
        runCount.getAndIncrement()
        Thread.sleep(1000)
      }, 0, 2L, TimeUnit.SECONDS)
    // TaskStart1...1sec...TaskFinish1...1sec...TaskStart2...1sec...TaskFinish2...1sec...TaskStart3...1sec...TaskFinish3
    // <--------------------------------------------5sec------------------------------------------------------------>
    Thread.sleep(5000)
    assert(runCount.get() == 3)
  }
  test ("run task per second - using scheduleAtFixedDelay") {
    val runCount = new AtomicInteger()
    val executorService = Executors.newSingleThreadScheduledExecutor()
      .scheduleWithFixedDelay(() => {  // Fixed Delay of 2 sec between two tasks
        runCount.getAndIncrement()
        Thread.sleep(1000)
      }, 0, 2L, TimeUnit.SECONDS)
    // TaskStart1.....1sec.....TaskFinish1............2sec..........TaskStart2.....1sec.....TaskFinish2.............2sec..
    // <--------------------------------------------5sec------------------------------------------------------------->
    Thread.sleep(5000)
    assert(runCount.get() == 2)
  }
}
