/*
 * Created by Saurav Sahu on 20/06/21, 3:56 PM.
 */

package com.functional.programming.exercises

import org.scalatest.FunSuite

class PolynomialTest extends FunSuite {
  test("combine terms and print polynomial - check exponent based ordering") {
    var expression = new Polynomial(Map(3 -> 10, 2 -> 20, 1 -> -30, 0 -> -40)) // pairs of (exponent, coefficient)
    assert(expression.toString == "10x^3 + 20x^2 - 30x - 40")
    // just shuffle the order
    expression = new Polynomial(Map( 1 -> -30, 3 -> 10, 2 -> 20, 0 -> -40))
    assert(expression.toString == "10x^3 + 20x^2 - 30x - 40")
  }
  test("combine terms and print polynomial - zero coefficient") {
    var expression = new Polynomial(Map(3 -> 10, 2 -> 20, 1 -> -30, 0 -> 0))
    assert(expression.toString == "10x^3 + 20x^2 - 30x")
  }
  test("combine terms and print polynomial - negative coefficient and negative exponent") {
    var expression = new Polynomial(Map(3 -> -10, 2 -> 20, -1 -> -30, 0 -> 0))
    assert(expression.toString == "-10x^3 + 20x^2 - 30x^-1")
  }
  test("perform addition of polynomials") {
    var expression1 = new Polynomial(Map(3 -> -10, 2 -> 20, -1 -> -30, 0 -> 0))
    var expression2 = new Polynomial(Map(3 -> 10, 2 -> 20, 1 -> -30, 0 -> -40)) // pairs of (exponent, coefficient)
    assert((expression1 + expression2).toString == "40x^2 - 30x - 40 - 30x^-1")
  }
  test("use bindings (instead of Map) to form polynomials - perform subtraction of polynomials") {
    var expression1 = new Polynomial(3 -> -10, 2 -> 20, -1 -> -30, 0 -> 0)
    var expression2 = new Polynomial(3 -> 10, 2 -> 20, 1 -> -30, 0 -> -40)
    assert((expression1 - expression2).toString == "-20x^3 + 30x + 40 - 30x^-1")
  }
}
