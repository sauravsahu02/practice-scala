/*
 * Created by Saurav Sahu on 19/06/21, 8:06 PM.
 */

package com.concurrency.examples

import java.util.concurrent.atomic.AtomicInteger

import com.google.common.util.concurrent.AtomicDouble
import com.typesafe.scalalogging.LazyLogging

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ExponentialBackoffDemo extends LazyLogging {
  @tailrec
  def retry[T](n: Long, delayInMillis: Long, offset: Double)(fn: => T) : T =
    Try(fn) match {
      case Success(value) if !value.asInstanceOf[Double].isNaN =>
        logger.info(value.toString)
        value
      case Failure(exception) if n < 0 =>
        throw exception
      case _ =>  // Both cases: Success(NaN) and Failure
        logger.info(f"retries left: $n")
        logger.info(s"sleeping for $delayInMillis ms before next retry...")
        Thread.sleep(delayInMillis)
        retry(n-1, (delayInMillis * offset).asInstanceOf[Number].longValue(), offset)(fn)
    }
}
class AccountDetails{
  private var totalSaving: AtomicDouble = new AtomicDouble()
  private var totalIncome: AtomicInteger = new AtomicInteger()
  def getSavingPercentage : Double = {
    if (calculationOngoing.get() != 0) throw new UnsupportedOperationException
    (totalSaving.get() * 100.0) / totalIncome.get()
  }

  def setTotalSaving(newValue: Double): Unit = totalSaving.set(newValue)
  def setTotalIncome(newValue: Int): Unit = totalIncome.set(newValue)

  private val calculationOngoing = new AtomicInteger()
  val calculateSaving = new Runnable {
    override def run = {
      calculationOngoing.incrementAndGet()
      Thread.sleep(2000)
      setTotalSaving(2000)
      calculationOngoing.decrementAndGet()
    }
  }
  val calculateIncome = new Runnable {
    override def run = {
      calculationOngoing.incrementAndGet()
      Thread.sleep(5000)
      setTotalIncome(5000)
      calculationOngoing.decrementAndGet()
    }
  }

}