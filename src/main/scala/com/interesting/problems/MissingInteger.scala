package com.interesting.problems
/**
 * Created by Saurav Sahu - 23-May-2021
 */
object MissingInteger{

  def find(arr: Array[Int]): Int = {
    var tmp : Array[Int] = arr.clone()  // rules out inplace approach
    arr.filter(x => x > 0 && x <= tmp.length).foreach(v => tmp(v - 1) = v)
    tmp.zipWithIndex.find(x => x._1 != (x._2+1)) match {
      case Some(ans) => ans._2 + 1
      case None => arr.length + 1
    }
  }

  def findInPlace(arr: Array[Int]): Int = {
    var i = 0
    while (i < arr.length){
      val value = arr(i)
      if (value >= 1 && value <= arr.length && value != i+1) {
        swapValuesInArray(arr, value - 1, i) // resulting to: arr(value-1) = value
      }else {
        i += 1
      }
    }
    var k = 0
    while (k < arr.length){
      if (arr(k) != k+1) return k+1
      k += 1
    }
    arr.length + 1
  }

  def swapValuesInArray(arr : Array[Int], thisIndex : Int, thatIndex : Int): Unit = {
    val tmp = arr(thisIndex)
    arr(thisIndex) = arr(thatIndex)
    arr(thatIndex) = tmp
  }
}
