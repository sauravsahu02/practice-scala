/*
 * Created by Saurav Sahu on 07/11/21, 5:30 PM.
 */

package com.functional.programming.exercises

abstract class JSON

case class JSeq(elems: List[JSON]) extends JSON
case class JObj(bindings: Map[String, JSON]) extends JSON
case class JNum(num: Double) extends JSON
case class JStr(string: String) extends JSON
case class JBool(b: Boolean) extends JSON

// Represent this JSON:
/* {
    "fname" : "John",
    "lname" : "Smith",
    "address": {
        "country": "India"
        "city": "Blr"
        "postal": 560001
        }
     "contact": [
        {"type": "Home", "num" : "91 999999999"},
        {"type": "Office", "num" : "91 999999998"},
      ]
   }
  * */


object JsonDemo extends App{
  val x = 10
  val data = JObj(Map(
    "fname" -> JStr("John"),
    "lname" -> JStr("Smith"),
    "address" -> JObj(Map(
      "country" -> JStr("India"),
      "city" -> JStr("Blr"),
      "postal" -> JNum(560001)
    )),
    "contact" -> JSeq(List(
      JObj(Map(
        "type" -> JStr("Home"), "num" -> JStr("91 999999999")
      )),
      JObj(Map(
        "type" -> JStr("Office"), "num" -> JStr("91 999999998")
      ))
    ))
  ))

  def show(json: JSON): String = {
    json match {
      case JObj(jmap) => "{" + jmap.map{case (k, json) => k + ": " + show(json)}.mkString(", ") + "}"
      case JStr(string) => "\"" + string + "\""
      case JSeq(list) => "[" + list.map(elem => show(elem)).mkString(",") + "]"
      case JNum(num) => num.toString
      case JBool(b) =>  b.toString
    }
  }
  println(show(data))
}
