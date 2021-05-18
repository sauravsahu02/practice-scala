package functional.programming.exercises

object Fibonacci {
  /** EXERCISE 1 (optional): Write a function to get the nth Fibonacci number. The
      first two Fibonacci numbers are 0 and 1, and the next number is always the sum of
      the previous two. Your definition should use a local tail-recursive function
  */
  def getNthFib(n : Int) : Int = {
    @annotation.tailrec
    def fib(s1 : Int, s2 :Int, n: Int) : Int = {
      if (n == 0) s1
      else fib(s2, s1+s2, n-1)
    }
    fib(0, 1, n)
  }
  def main(args: Array[String]): Unit = {

  }
}
