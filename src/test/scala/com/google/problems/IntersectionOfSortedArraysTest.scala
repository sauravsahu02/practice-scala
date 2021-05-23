package com.google.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class IntersectionOfSortedArraysTest extends FunSuite {
  test("Test1 - nonempty arrays"){
    val (x, y) = (5, 10)
    val input1 = (1 to y).toArray
    val input2 = (x to 15).toArray
    assert(new IntersectionOfSortedArrays().getIntersectionResult(input1, input2).sameElements((x to y).toArray[Int]))
    assert(new IntersectionOfSortedArrays().getIntersectionResult(input2, input1).sameElements((x to y).toArray[Int]))
  }
  test("Test2 - nonempty arrays - with duplicates"){
    val (x, y) = (5, 10)
    val input1 = (1 to y).toArray ++ Array(y)
    val input2 = Array(x) ++ (x to 15).toArray
    assert(new IntersectionOfSortedArrays().getIntersectionResult(input1, input2).sameElements((x to y).toArray[Int]))
    assert(new IntersectionOfSortedArrays().getIntersectionResult(input2, input1).sameElements((x to y).toArray[Int]))
  }
  test("Test3 - empty and non-empty arrays"){
    assert(new IntersectionOfSortedArrays().getIntersectionResult((1 to 5).toArray, Array.empty).isEmpty)
  }
  test("Test4 - empty arrays"){
    assert(new IntersectionOfSortedArrays().getIntersectionResult(Array.empty, Array.empty).isEmpty)
  }
}
