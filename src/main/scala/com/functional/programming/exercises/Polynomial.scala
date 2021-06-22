/*
 * Created by Saurav Sahu on 20/06/21
 */

package com.functional.programming.exercises
/**
 * Polynomial stores terms in (exponent,coefficient) form. e.g. (3,-2) is stored for term `-2x^3`.
 * Support for addition and subtraction of two polynomials.
 */
class Polynomial(termsInput : Map[Int, Int]) {
  def this(pairs: (Int, Int)*) = this(pairs.toMap)
  val terms = termsInput.withDefaultValue(0) // default value is critical for addition and subtraction
  def addTerm(terms: Map[Int, Int], term: (Int, Int)) : Map[Int, Int] = {
    val (exp, coeff) = term
    terms.updated(exp, coeff +  terms(exp))
  }
  def subtractTerm(terms: Map[Int, Int], term: (Int, Int)) : Map[Int, Int] = {
    val (exp, coeff) = term
    terms.updated(exp, terms(exp) - coeff)
  }
  def +(other: Polynomial): Polynomial = {
    new Polynomial(other.terms.foldLeft(terms)(addTerm))
  }
  def -(other: Polynomial): Polynomial = {
    new Polynomial(other.terms.foldLeft(terms)(subtractTerm))
  }
  override def toString: String = {
    var termsArray =
      for {
        (exp, coeff) <- terms.toList.sorted.reverse
        if coeff != 0
      } yield {
        val exponent = exp match{
          case 0 => s""
          case 1 => s"x"
          case y => s"x^$y"
        }
        if (coeff > 0) s"+$coeff$exponent"
        else if (coeff < 0) s"$coeff$exponent"
        else s""
      }
    if (terms.isEmpty) "0"
    else termsArray.mkString(" ").stripPrefix("+")
      .replace(" +", " + ")
      .replace(" -", " - ")
  }
}
