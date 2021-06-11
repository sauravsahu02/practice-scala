package com.concurrency.examples

import java.util.concurrent._

import com.concurrency.examples.BlockingQueueDemo._
import org.scalatest.FunSuite
import org.scalatest.Matchers.{noException, thrownBy}

class BlockingQueueDemoTest extends FunSuite {
  test("using SynchronousQueue - execute with sufficient pool size") {
    def executeWithEnoughPoolSize = {
      val executor : ExecutorService = new ThreadPoolExecutor(1, 2, 0L,
        TimeUnit.MILLISECONDS, new SynchronousQueue[Runnable]())
      executor.execute(SlowProcess("First"))
      executor.execute(SlowProcess("Second"))
    }
    noException shouldBe thrownBy(executeWithEnoughPoolSize)
  }
  test("using SynchronousQueue - execute with insufficient pool-size - leading to exception") {
    def executeWithInsufficientPoolSize = {
      val executor : ExecutorService = new ThreadPoolExecutor(1, 2, 0L,
        TimeUnit.MILLISECONDS, new SynchronousQueue[Runnable]())
      executor.execute(SlowProcess("First"))
      executor.execute(SlowProcess("Second"))
      executor.execute(SlowProcess("Third"))
      executor.shutdown()
      executor.awaitTermination(1, TimeUnit.HOURS)
    }
    assertThrows[RejectedExecutionException]{executeWithInsufficientPoolSize}
  }
  test("handle insufficient pool-size using ArrayBlockingQueue - backpressure handling") {
    def executeWithInsufficientPoolSize = {
      val executor : ExecutorService = new ThreadPoolExecutor(2, 2, 1L,
        TimeUnit.HOURS, new ThreadPoolQueue("WaitsUntilThereIsSpace"))
      // automatically waits until there is space in the queue for the new task.
      executor.execute(SlowProcess("first task for ArrayBlockingQueue"))
      executor.execute(SlowProcess("second task for ArrayBlockingQueue"))
      executor.execute(SlowProcess("third task for ArrayBlockingQueue"))
      executor.shutdown()
      executor.awaitTermination(1, TimeUnit.HOURS)
    }
    noException shouldBe thrownBy(executeWithInsufficientPoolSize)
  }
}
