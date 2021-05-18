package google.problems

import org.scalatest.{BeforeAndAfter, FunSuite}

class ContiguousSubarraySumWithinRangeTest extends FunSuite with BeforeAndAfter{

  test("Input 1") {
    val input = Array(1,2,3)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(0, 3) == 4)
    // 1, 2, 3, (1,2)
  }
  test("Input 2") {
    val input = Array(-2,5,-1)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(-2, 2) == 3)
    // (-2), (-1), (-2, 5, -1)
  }
  test("Input 3") {
    val input = Array(2,-1,2)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(1, 3) == 5)
    // (2), (2, -1), (2), (-1, 2), (2, -1, 2)
  }
  test("Input 4") {
    val input = Array(10, -10, 10, -10)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(-10, 10) == 10)
  }

}
