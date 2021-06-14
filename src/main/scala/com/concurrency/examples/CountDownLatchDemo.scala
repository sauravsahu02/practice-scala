package com.concurrency.examples

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicLong

import com.typesafe.scalalogging.LazyLogging
/**
 * Created by Saurav Sahu on 14th June 2021
 */
object CountDownLatchDemo extends LazyLogging {
  final val sleepTime = 3000
  def apply(latch: CountDownLatch, makeWaitIndefinite : Boolean): CountDownLatchDemo = new CountDownLatchDemo {
    final val counter = new AtomicLong(latch.getCount)
    override def run() = {
      try {
        Thread.sleep(sleepTime)
      } catch {
        case e : InterruptedException => logger.error(e.getMessage)
      }
      if (!makeWaitIndefinite) latch.countDown() // No countDown results into infinite waiting
    }
  }
}
trait CountDownLatchDemo extends Runnable
