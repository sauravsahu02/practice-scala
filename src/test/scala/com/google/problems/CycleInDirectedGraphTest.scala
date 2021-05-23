package com.google.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu - 23-May-2021
 */
class CycleInDirectedGraphTest extends FunSuite {
  test("Graph 1 : Self Cycle"){
    val graph = Array((0,1), (0,2), (1,2), (2, 0), (2,3), (3, 3))
    // 0 -> (1,2); 1 -> 2; 2 -> (0,3); 3 -> 3 has cycle
    assert(new CycleInDirectedGraph(graph).hasCycle)
  }
  test("Graph 2 : No Cycle"){
    val graph = Array((1,2), (1,3), (2,4), (3, 4))
    assert(!new CycleInDirectedGraph(graph).hasCycle)
  }
  test("Graph 3 : Simple Cycle"){
    val graph = Array((1,2), (2,1))
    assert(new CycleInDirectedGraph(graph).hasCycle)
  }
}
