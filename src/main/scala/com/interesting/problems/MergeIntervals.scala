package com.interesting.problems

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting
import scala.collection.immutable.ArraySeq.unsafeWrapArray
/**
 * Created by Saurav Sahu - 23-May-2021
 */

object MergeIntervals{
  def apply(input : Array[(Int, Int)]) : Array[(Int, Int)] = {
    Sorting.stableSort(input)
    // Scala 2.13 feature : use scala.collection.immutable.ArraySeq.unsafeWrapArray
    // to wrap your array and avoid copying, but see its scaladoc
    val inputArrayBuffer = ArrayBuffer(unsafeWrapArray(input):_*)  // note : array:_* gives warning in Scala 2.13
    var idx = 0
    while ( idx < inputArrayBuffer.size - 1) {
      val curr = inputArrayBuffer(idx)
      val next = inputArrayBuffer(idx+1)
      if (curr._2 >= next._1) {
        inputArrayBuffer(idx) = (curr._1, curr._2 max next._2)
        inputArrayBuffer.remove(idx+1) // Array doesn't allow remove using index. So have to use ArrayBuffer
      }else {
        idx += 1
      }
    }
    inputArrayBuffer.toArray
  }
}
