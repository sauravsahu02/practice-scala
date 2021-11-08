/*
 * Created by Saurav Sahu on 08/11/21, 4:01 PM.
 */

package com.functional.programming.exercises

import com.functional.programming.exercises.SymbolDemo._


package object SymbolDemo {
  type DataField = Symbol

  object DataField extends (String => DataField) {
    override def apply(string: String) : DataField = Symbol(string)
    def unapply(field: DataField) : Option[String] = Some(field.name)
  }
  // allows implicit conversion of String to DataField
  implicit def getDataField(string: String): DataField = DataField(string)
}

object Demo extends App{
  val x = DataField("Hello")
  println(x)
  val x_copy = x
  val x_implicit : DataField = "Hello2" // implicit conversion happens
  println(x_implicit)
}
