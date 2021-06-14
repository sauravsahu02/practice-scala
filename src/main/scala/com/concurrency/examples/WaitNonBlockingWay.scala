package com.concurrency.examples

import java.util.concurrent.atomic.AtomicReference

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
 * Created by Saurav Sahu on 13th June 2021
 */
object WaitingNonBlockingWay{ // by registering a callback on the future
  final val SuccessRcvdMsg = "Success Received"
  final val WaitingMsg = "...Still waiting..."
  final val recordProgress = new AtomicReference[String]("")
  final val waitingTime = 4000
  def performAsyncWait ={
    val sleepsForSometimeFuture : Future[String] = Future( {
      Thread.sleep(waitingTime)
      "Done"
    })
    sleepsForSometimeFuture onComplete {
      case Success(value) => recordProgress.getAndAccumulate(SuccessRcvdMsg, (u,v) => u + v) //order matters for testing
      case Failure(exception) => throw exception
    }
    recordProgress.getAndAccumulate(WaitingMsg, (u,v) => u + v)
  }
}
