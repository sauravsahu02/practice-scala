/*
 * Created by Saurav Sahu on 26/06/21, 8:33 PM.
 */

package com.interesting.problems

import scala.collection.mutable
/**
 * Created by Saurav Sahu - 21-May-2021
 */
class FindLargestSubset(val inputArr: Array[Int]) {
  // Given an int array which might contain duplicates, find the largest subset of
  // it which form a sequence.
  // Eg. {1,6,10,4,7,9,5}
  // then ans is 4,5,6,7
  // Sorting is an obvious solution. Can this be done in O(n) time

  def getLength: Int = {

    val mapToOtherEnd = mutable.HashMap[Int, Int]()

    def getLengthAfterJoiningSeq(leftSeqEnd : Int, rightSeqStart : Int): Int ={
      val newStart = mapToOtherEnd(leftSeqEnd)
      val newEnd = mapToOtherEnd(rightSeqStart)
      mapToOtherEnd.update(newEnd, newStart)
      mapToOtherEnd.update(newStart, newEnd)
      newEnd - newStart + 1
    }

    var lengthOfLongestSeq : Int = 0
    inputArr.foreach {
      x => {
        if (!mapToOtherEnd.contains(x)) {
          mapToOtherEnd.update(x, x)
          if (x != Int.MinValue && mapToOtherEnd.contains(x - 1)) {
            lengthOfLongestSeq = lengthOfLongestSeq max getLengthAfterJoiningSeq(x - 1, x)
          }
          if (x != Int.MaxValue && mapToOtherEnd.contains(x + 1)) {
            lengthOfLongestSeq = lengthOfLongestSeq max getLengthAfterJoiningSeq(x, x + 1)
          }
        }
      }
    }
    lengthOfLongestSeq
  }
}