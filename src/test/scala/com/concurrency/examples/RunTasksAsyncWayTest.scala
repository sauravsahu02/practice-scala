package com.concurrency.examples

import java.util.concurrent.Executors

import com.concurrency.examples.RunTasksAsyncWay._
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.concurrent.ExecutionContext
import scala.math.floor

class RunTasksAsyncWayTest extends FunSuite with BeforeAndAfter{
  before{
    RunTasksAsyncWay.counter.set(0)
  }
  test("run asynchronously with single thread"){
    val executionContextExecutorOneThread = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1))
    val t1 = System.nanoTime
    RunTasksAsyncWay(executionContextExecutorOneThread)
    assert(floor((System.nanoTime - t1) / 1e9d) == RunTasksAsyncWay.sleepTimeInSecs * nFuture / 1)
    assert(RunTasksAsyncWay.counter.get() == 4)
  }
  test("run asynchronously with two threads"){
    val executionContextExecutorTwoThread = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))
    val t1 = System.nanoTime
    RunTasksAsyncWay(executionContextExecutorTwoThread)
    assert(floor((System.nanoTime - t1) / 1e9d) == RunTasksAsyncWay.sleepTimeInSecs * nFuture / 2)
    assert(RunTasksAsyncWay.counter.get() == 4)
  }
  test("run asynchronously with four threads"){
    val executionContextExecutorFourThread = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
    val t1 = System.nanoTime
    RunTasksAsyncWay(executionContextExecutorFourThread)
    assert(floor((System.nanoTime - t1) / 1e9d) == RunTasksAsyncWay.sleepTimeInSecs * nFuture / 4)
    assert(RunTasksAsyncWay.counter.get() == 4)
  }
}
