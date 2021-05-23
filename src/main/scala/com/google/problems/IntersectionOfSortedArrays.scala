package com.google.problems

import scala.collection.mutable.ListBuffer
/**
 * Created by Saurav Sahu - 22-May-2021
 */
class IntersectionOfSortedArrays {
  def getIntersectionResult(arr1 : Array[Int], arr2 : Array[Int]) : Array[Int] = {
    var i = 0
    var j = 0
    // Note that Arrays are mutable, indexed collections of values,
    // but the append operation is not supported.
    val listBuffer = new ListBuffer[Int]()  // ListBuffer can perform append operation in Constant time
    while (i < arr1.length && j < arr2.length){
      if (arr1(i) == arr2(j)) {
        listBuffer += arr1(i) // common mistake: performing this after updating index causes ArrayIndexOutOfBoundException
        i += 1
        j += 1
      }
      else if (arr1(i) < arr2(j)) {
        i += 1
      }
      else {
        j += 1
      }
    }
    listBuffer.toArray
  }
}
