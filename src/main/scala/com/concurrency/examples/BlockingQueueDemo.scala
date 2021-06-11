package com.concurrency.examples

import java.util.concurrent.ArrayBlockingQueue

/**
 * Created by Saurav Sahu on 11th June 2021
 */

object BlockingQueueDemo {
  final class ThreadPoolQueue(name: String) extends ArrayBlockingQueue[Runnable](1){
    override def offer(runnable: Runnable): Boolean ={
      try{
        put(runnable)
      } catch {
        case e: InterruptedException =>
          println(f"The ThreadPoolQueue running thread $name was interrupted")
          return false
      }
      true
    }
  }
  class SlowProcess(name : String) extends Runnable{
    override def run(): Unit = {
      try{
        println(f"Running $name thread.")
        Thread.sleep(100)
      } catch {
        case e: InterruptedException =>
          println(f"The SlowProcess running thread $name was interrupted")
          Thread.currentThread().interrupt()
      }
    }
  }
  object SlowProcess {
    def apply(name: String): Runnable = new SlowProcess(name)
  }
}

