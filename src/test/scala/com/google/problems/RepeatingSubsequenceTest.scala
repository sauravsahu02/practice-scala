package com.google.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class RepeatingSubsequenceTest extends FunSuite {
  // Below flag for the obscurity of the problem
  // see here, https://www.careercup.com/question?id=5931067269709824
  val isOverlappingSubsequenceRangeAllowed = true
  test("Case - abab"){
    val input = "abab"
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - aaa"){
    val input = "aaa"
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - aaaa"){
    val input = "aaaa"
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - abba"){
    val input = "abba"
    assert(!new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - abcbda"){
    val input = "abcbda"
    assert(!new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - nambaefgbk"){
    val input = "nambaefgbk"  // ab
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - empty"){
    val input = ""
    assert(!new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - aabb"){
    val input = "aabb"
    assert(new RepeatingSubsequence().hasSubsequences(input) == isOverlappingSubsequenceRangeAllowed) // ab
  }
  test("Case - abbb - The start of second subsequence coincides with the end of first one"){
    val input = "abbb"  // bb
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - eeabace"){
    val input = "eeabace"
    assert(new RepeatingSubsequence().hasSubsequences(input) == isOverlappingSubsequenceRangeAllowed) // ea
  }
  test("Case - eeabaceb"){
    val input = "eeabaceb" // eb
    assert(new RepeatingSubsequence().hasSubsequences(input))
  }
  test("Case - a"){
    val input = "a"
    assert(!new RepeatingSubsequence().hasSubsequences(input))
  }
}
