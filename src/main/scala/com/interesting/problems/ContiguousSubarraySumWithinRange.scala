/*
 * Created by Saurav Sahu on 26/06/21, 8:33 PM.
 */

package com.interesting.problems

import scala.collection.mutable

class ContiguousSubarraySumWithinRange(val inputArr: Array[Int]){
  def getCount(lowerBound: Int, upperBound: Int): Int = {
    var cumSum = 0
    var count = 0
    val treeSet : mutable.TreeSet[(Int, Int)] = mutable.TreeSet.empty
    treeSet.addOne((0, -1))
    for (index <- inputArr.indices){
      cumSum += inputArr(index)
      count += treeSet.range((cumSum - upperBound, -1), (cumSum-lowerBound+1, -1)).size
      treeSet.addOne((cumSum, index))
    }
    count
  }
}

/*
 https://www.careercup.com/question?id=5200686994161664
 Given an array int32 arr[] of size n, return the number of non-empty contiguous sub-arrays whose sum lies in range [a, b]

 That is, implement the following naive algorithm faster than O(n^2)

 def naive_algorithm(lst, a, b):
 result = 0
 for i in xrange(len(lst)):
 for j in xrange(i, len(lst)):
 if a <= sum(lst[i:j + 1]) <= b:
   result += 1
 return result

 Examples:
 count([1,2,3], 0, 3) = 5 # [1], [2], [3], [1, 2], [3]
 count([-2,5,-1], -2, 2) = 3 # [-2], [-1], [-2, 5, -1]
 You may assume that there are no overflows, that is sum(|x_i|) <= MAX_INT - 1
 */
// Algorithm Explained:
// Consider input Array(-2, 5, -1) and Range: [-2, 2]
// -2 | CumSum: -2 ; SelfInRange ; no candidate exists | TreeSet((-2,0))
// -2 5 | CumSum : 3 ; SelfNotInRange; CandidatesToBeInRange [3-2, 3-(-2)] : [1, 5] : no candidate found | TreeSet((-2,0), (3,1))
// -2 5 -1 | CumSum : 2 ; SelfInRange; CandidatesToBeInRange [2-2, 2+2] : [0, 4] : RightCandidate (3,1) | TreeSet((-2,0), (3,1), (2, 1))
// Ans is 3 = [-2 (SelfInRange), -1 (SelfInRange), (3,1) (RightCandidate)]