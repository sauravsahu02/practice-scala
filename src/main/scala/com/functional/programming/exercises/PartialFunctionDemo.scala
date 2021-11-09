/*
 * Created by Saurav Sahu on 08/11/21, 8:07 PM.
 */

package com.functional.programming.exercises

object PartialFunctionDemo extends App{
  val squareRootImplicit: PartialFunction[Double, Double] = {
    case x if x >= 0 => Math.sqrt(x)
  }
  println(squareRootImplicit(25)) // prints 5.0
//  println(squareRootImplicit(-25)) // return a scala.MatchError runtime error.

  val negativeOrZeroToPositive: PartialFunction[Int, Int] = {
    case x if x <= 0 => Math.abs(x)
  }
  val positiveToNegative: PartialFunction[Int, Int] = {
    case x if x > 0 => -1 * x
  }

  val swapSign: PartialFunction[Int, Int] = {
    negativeOrZeroToPositive orElse positiveToNegative // chaining is possible with Partial functions.
  }

  println(swapSign(10)) // no scala.MatchError
  println(swapSign(0))
  println(swapSign(-10))
}
