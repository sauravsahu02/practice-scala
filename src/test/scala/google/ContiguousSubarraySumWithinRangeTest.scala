package google
class ContiguousSubarraySumWithinRangeTest extends FunSuite with BeforeAndAfter{

  test("Input 1") {
    val input = Array(1,2,3)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(0, 3) == 3)
  }
  test("Input 2") {
    val input = Array(-2,5,-1)
    assert(new ContiguousSubarraySumWithinRange(input).getCount(-2, 2) == 3)
  }
}
