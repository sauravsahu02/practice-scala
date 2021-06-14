package com.concurrency.examples

import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContextExecutor, Future, blocking}
/**
 * Created by Saurav Sahu on 11th June 2021
 */
object RunTasksAsyncWay extends LazyLogging {
  final val sleepTimeInSecs = 5
  final val nFuture = 4
  val counter = new AtomicInteger()
  def apply(executionContextExecutor: ExecutionContextExecutor) = {
    val futures = new ListBuffer[Future[Unit]]
    val t1 = System.nanoTime
    (1 to nFuture) foreach { n =>
      futures.append(Future {
        logger.info("starting Future: " + n)
        blocking {
          counter.incrementAndGet()
          Thread.sleep(sleepTimeInSecs * 1000)
        }
        logger.info("ending Future: " + n)
      }(executionContextExecutor))
    }
    import scala.concurrent.ExecutionContext.Implicits.global
    val aggFutures = for { // for-comprehension
      f1 <- futures.head
      f2 <- futures(1)
      f3 <- futures(2)
      f4 <- futures(3)
      } yield (f1, f2, f3, f4)
    Await.ready(aggFutures, Duration.Inf)
  }
}