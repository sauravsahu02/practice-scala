package com.functional.programming.exercises

import org.scalatest.WordSpec

class EightQueenProblemTest extends WordSpec {
  "number of solution for - safe-positions of 5 queens" in {
    assert(EightQueenProblem.findPositionForNQueens(5).size == 10)
  }
  "number of solution for - safe-positions of 4 queens" in {
    assert(EightQueenProblem.findPositionForNQueens(4).size == 2)
  }
  "number of solution for - safe-positions of 3 queens" in {
    assert(EightQueenProblem.findPositionForNQueens(0).equals(Set(List())))
  }
}
