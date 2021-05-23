package com.functional.programming.exercises

import org.scalatest.FunSuite

class FibonacciTest extends FunSuite {
  test("Fib 0") {
    assert(Fibonacci.getNthFib(0) == 0)
  }
  test("Fib 1") {
    assert(Fibonacci.getNthFib(1) == 1)
  }
  test("Fib 2") {
    assert(Fibonacci.getNthFib(2) == 1)
  }
  test("Fib 3") {
    assert(Fibonacci.getNthFib(3) == 2)
  }
  test("Fib 4") {
    assert(Fibonacci.getNthFib(4) == 3)
  }
  test("Fib 5") {
    assert(Fibonacci.getNthFib(5) == 5)
  }
  test("Fib 6") {
    assert(Fibonacci.getNthFib(6) == 8)
  }
}
