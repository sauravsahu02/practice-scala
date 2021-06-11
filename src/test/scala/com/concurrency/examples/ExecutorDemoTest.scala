package com.concurrency.examples

import com.concurrency.examples.ExecutorDemo.Task
import org.scalatest.FunSuite

class ExecutorDemoTest extends FunSuite {
  test("Increment counter"){
    assert(ExecutorDemo.getCount() == 0)
    Invoker.execute(Task)
    assert(ExecutorDemo.getCount() == ExecutorDemo.times)
  }
  test("Run task N times"){
    ExecutorDemo.setCount(0)
    assert(ExecutorDemo.getCount() == 0)
    val N = 100
    for (c <- 1 to N){
      Invoker.execute(Task)
    }
    assert(ExecutorDemo.getCount() == N * ExecutorDemo.times)
  }
}
