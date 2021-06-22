package com.interesting.problems

import org.scalatest.FunSuite
import scala.util.Sorting
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class MergeIntervalsTest extends FunSuite {

  test("test - Sorting Array of tuples - Inplace") {
    var input = Array((1,4), (9,111), (2,10), (15,28))
    var expectedOutput = Array((1,4), (2,10), (9,111), (15,28))
    Sorting.stableSort(input)
    assert(input.sameElements(expectedOutput))
  }
  test ("test - all merges into one"){
    var input = Array((1,4), (9,111), (2,10), (15,28))
    var expectedOutput = Array((1,111))
    assert(MergeIntervals(input).sameElements(expectedOutput))
  }
  test ("test - merges some of them"){
    var input = Array((1,4), (9,10), (2,10), (15,28))
    var expectedOutput = Array((1,10), (15,28))
    assert(MergeIntervals(input).sameElements(expectedOutput))
  }
  test ("test - merges none of them"){
    var input = Array((1,4), (9,10), (5,7), (15,28))
    var expectedOutput = Array((1,4), (5,7), (9,10), (15,28))
    assert(MergeIntervals(input).sameElements(expectedOutput))
  }
}
