package google.problems

class ContiguousSubarraySumWithinRange(val inputArr: Array[Int]) extends SingleIntArrayOperation(inputArr){
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
 count([1,2,3], 0, 3) = 3 # [1], [2], [3], [1, 2], [3]
 count([-2,5,-1], -2, 2) = 3 # [-2], [-1], [-2, 5, -1]
 You may assume that there are no overflows, that is sum(|x_i|) <= MAX_INT - 1
 */

  def getCount(lowerBound: Int, upperBound: Int): Int = ???
}