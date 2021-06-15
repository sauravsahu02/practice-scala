package com.functional.programming.exercises


object EightQueenProblem {
  /**
   * The eight queens problem is the problem of placing eight queens on an 8×8 chessboard such that none of
   * them attack one another (no two are in the same row, column, or diagonal).
   */
  def findPositionForNQueens(n : Int): Set[List[Int]] = { // note: each list represents one solution
    /* Returns a set of lists, where each list contains column num of queens */
    def placeQueens(k : Int) : Set[List[Int]]  =
      if (k == 0) Set(List())
      else
        for { q <- placeQueens(k - 1)
              col <- 0 until n  // try for every possible column
              if isSafePos(col, q) } yield col :: q
    placeQueens(n)
  }

  /* Determines if position [queens.length, col] is safe for new Kth queen or not */
  def isSafePos(col: Int, queens: List[Int]) : Boolean = {
    val nRows = queens.length    // row number for the new queen to be positioned
    val rowOfQueens = (nRows - 1 to 0 by -1) zip queens
    rowOfQueens forall {
      case (r, c) => c != col &&
        (nRows - r) != math.abs(col - c) // not in check with other queen diagonally
    }
  }
  def printQueenPos(queens: List[Int]): Unit = {
    queens foreach  {
      col => println(Vector.fill(queens.length)("* ").updated(col, "X "))
    }
    println("------------------------")
  }
}

