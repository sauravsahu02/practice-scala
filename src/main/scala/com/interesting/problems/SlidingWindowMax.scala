package com.interesting.problems

import java.util
/**
 * Created by Saurav Sahu - 23-May-2021
 */
object SlidingWindowMax{
  val WindowErrorMessage = f"window size value should be less than or equal to input arr size, and greater than 0"
  def apply(arr : Array[Int], windowSize : Int) : Array[Int] = {
    if (arr.isEmpty) return arr
    require(windowSize > 0 && arr.length > windowSize, WindowErrorMessage)

    var ans = new Array[Int](arr.length-windowSize+1)
    var deq = new util.ArrayDeque[Int]()

    var currIndex = 0
    for (v <- arr){
      if (deq.isEmpty)
        deq.addLast(currIndex)
      else{
        if (deq.getFirst == currIndex-3){
          deq.removeFirst()
        }
        while( !deq.isEmpty &&
                arr(deq.getLast) <= v ){
          deq.removeLast()
        }
        deq.addLast(currIndex)
      }
      if (currIndex+1 >= windowSize) {
        ans(currIndex+1-windowSize) = arr(deq.getFirst)
      }
      currIndex += 1
    }
    ans
  }
}