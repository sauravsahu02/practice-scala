package com.interesting.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23 May 2021
 */
class SlidingWindowMaxTest extends FunSuite {

  test("testGetSlidingWindowMax") {
    val inputArr = Array(1, 3, -1, -3, 5, 3, 6, 7)
    assert(SlidingWindowMax(inputArr, 3).sameElements(Array(3, 3, 5, 5, 6, 7)))
  }
  test("testGetSlidingWindowMax - empty array") {
    assert(SlidingWindowMax(Array.empty, 3).sameElements(Array.empty[Int]))
  }
  // Negative tests follows
  test("testGetSlidingWindowMax - empty window") {
    val inputArr = Array(1, 3, -1, -3, 5, 3, 6, 7)
    val thrown = intercept[IllegalArgumentException]{
      SlidingWindowMax(inputArr, 0)
    }
    assert(thrown.getMessage.contains(SlidingWindowMax.WindowErrorMessage))
  }
  test("testGetSlidingWindowMax - too large window") {
    val inputArr = Array(1, 3, -1, -3, 5, 3, 6, 7)
    val thrown = intercept[IllegalArgumentException] {
      SlidingWindowMax(inputArr, inputArr.length+1)
    }
    assert(thrown.getMessage.contains(SlidingWindowMax.WindowErrorMessage))
  }
}
