package com.concurrency.examples
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.scalalogging.LazyLogging

/**
 * Created by Saurav Sahu on 11th June 2021
 */

object BlockingQueueDemo extends LazyLogging{
  final class ThreadPoolQueue(name: String) extends ArrayBlockingQueue[Runnable](1){
    override def offer(runnable: Runnable): Boolean ={
      try{
        put(runnable)
      } catch {
        case e: InterruptedException =>
          logger.info(f"The ThreadPoolQueue running thread $name was interrupted")
          return false
      }
      true
    }
  }
  var runCount : AtomicInteger = new AtomicInteger()
  class SlowProcess(name : String) extends Runnable{
    override def run(): Unit = {
      try{
        logger.info(f"Running $name thread.")
        runCount.incrementAndGet()
        Thread.sleep(100)
      } catch {
        case e: InterruptedException =>
          logger.info(f"The SlowProcess running thread $name was interrupted")
          Thread.currentThread().interrupt()
      }
    }
  }
  object SlowProcess {
    def apply(name: String): Runnable = new SlowProcess(name)
  }
}

