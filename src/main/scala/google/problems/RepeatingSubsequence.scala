package google.problems

class RepeatingSubsequence {
  /*
  Given a string (1-d array) , find if there is any sub-sequence that repeats itself.
  Here, sub-sequence can be a non-contiguous pattern, with the same relative order.
  Eg:
  1. abab <------- yes, ab is repeated
  2. abba <------- No, a and b follow different order
  3. acbdaghfb <-- yes there is a followed by b at two places
  4. abcdacb <----- yes a followed by b twice

  The above should be applicable to ANY TWO (or every two) characters in the
  string and optimum over time.
  */
  def hasSubsequences(input: String) : Boolean ={
    val len = input.length
    var matrix = Array.ofDim[Int](len+1, len+1)
    for(x <- 1 to len){
      for(y <- 1 to len){
        val rowChar = input.charAt(x-1)
        val colChar = input.charAt(y-1)
        if (rowChar == colChar && x != y){
          matrix(x)(y) = 1 + matrix(x-1)(y-1)
        }else{
          matrix(x)(y) = matrix(x-1)(y) max matrix(x)(y-1)
        }
      }
    }
    matrix(len)(len) > 1
  }
}
