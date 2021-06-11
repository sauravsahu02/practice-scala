package com.concurrency.examples

import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Saurav Sahu on 11th June 2021
 */
object Invoker extends Executor{
  override def execute(command: Runnable): Unit = {  // An executor executes a runnable like a runnable runs.
    command.run()
  }
}

object ExecutorDemo {
  private final val counter = new AtomicLong
  def setCount(i: Int) = counter.set(i)
  final val times = 99999
  object Task extends Runnable {
    override def run() =
      for (x <- 1 to times)
        counter.incrementAndGet()
  }
  def getCount() = counter.get()
}