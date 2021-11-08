/*
 * Created by Saurav Sahu on 08/11/21, 5:22 PM.
 */

package com.functional.programming.exercises

// Linked List implementation with trait Node[+B] forming ListNode (head & tail_list) and Nil
trait Node[+B] {
  //  def append(elem: B): Node[B] // CERROR because the parameter elem in prepend is of type B, which we declared covariant.
  //  This doesn’t work because functions are contravariant in their parameter types and covariant in their result types.

  // To fix this, we need to flip the variance of the type of the parameter elem in append
  def prepend[U >: B](elem: U): Node[U] // introducing a new type parameter U that has B as a lower type bound.
}

case class ListNode[+B](h: B, t: Node[B]) extends Node[B]{
  override def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
  def head: B = h
  def tail: Node[B] = t
}

case class Nil[+B]() extends Node[B] {
  def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
}

trait Fish
case class Dolphin() extends Fish
case class Shark() extends Fish

object CovarianceDemo {
  class Slot[T](var some: T) {
    def get: T = some
  }
  class FixedGenericSlot[+T](val some: T){ // covariance allowed over val
    def get: T = some
  }
  //  class DynamicGenericSlot[+T](var some: T) { // CERROR: Covariant type T occurs in contravariant position in type T of value some
  //    def get: T = some
  //  }
  //  Read more: https://stackoverflow.com/a/20628788/1465553

  val dolphins = ListNode(Dolphin(), Nil())
  dolphins.prepend(Shark())
}