package com.interesting.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class MissingIntegerTest extends FunSuite {
  test("Test - find method") {
    assert(MissingInteger.find(Array(3, 4, -1, 1)) == 2)
    assert(MissingInteger.find(Array(1, 2, 0)) == 3)
    assert(MissingInteger.find(Array(-8, -7, -6)) == 1)
    assert(MissingInteger.find(Array(1, 2, 3)) == 4)
    assert(MissingInteger.find(Array.empty) == 1)
  }
  test ("Test - find in-place method"){
    assert(MissingInteger.findInPlace(Array(3, 4, -1, 1)) == 2)
    assert(MissingInteger.findInPlace(Array(1, 2, 0)) == 3)
    assert(MissingInteger.findInPlace(Array(-8, -7, -6)) == 1)
    assert(MissingInteger.findInPlace(Array(1, 2, 3)) == 4)
    assert(MissingInteger.findInPlace(Array.empty) == 1)
  }
}
