package com.google.problems

import com.interesting.problems.FindLargestSubset
import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class FindLargestSubsetTest extends FunSuite {
  test ("Test 1 - Random numbers") {
    val input = Array(1,6,10,4,7,9,5)
    val subsetLength = new FindLargestSubset(input).getLength
    assert(subsetLength == 4)  // 4,5,6,7
    val inputShuffled = scala.util.Random.shuffle(input).toArray
    assert(new FindLargestSubset(inputShuffled).getLength == subsetLength)
  }
  test ("Test 2 - Sorted already") {
    val input = Array(1,2,3,4,5)
    val subsetLength = new FindLargestSubset(input).getLength
    assert(subsetLength == 5)
    val inputShuffled = scala.util.Random.shuffle(input).toArray
    assert(new FindLargestSubset(inputShuffled).getLength == subsetLength)
  }
  test ("Test 3 - With duplicates") {
    val input = Array(1,2,3,3,4,4,5,6,6)
    val subsetLength = new FindLargestSubset(input).getLength
    assert(subsetLength == 6)  // 1,2,3,4,5,6
    val inputShuffled = scala.util.Random.shuffle(input).toArray
    assert(new FindLargestSubset(inputShuffled).getLength == subsetLength)
  }
  test("Test 4 - Empty array"){
    assert(new FindLargestSubset(Array.empty).getLength == 0)
  }
}
