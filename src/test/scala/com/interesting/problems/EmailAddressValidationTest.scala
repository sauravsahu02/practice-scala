package com.interesting.problems

import org.scalatest.FunSuite
/**
 * Created by Saurav Sahu on 10th June 2021
 */
class EmailAddressValidationTest extends FunSuite {
  test("local part has comment") {
    var result = EmailAddressValidation.getLocalPartAndDomain("john.smith(comment)@example.com")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
    result = EmailAddressValidation.getLocalPartAndDomain("john.smith(comment)@example.com")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
    result = EmailAddressValidation.getLocalPartAndDomain("(comment)john.smith(comment)@example.com")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
  }
  test("domain part has comment") {
    var result = EmailAddressValidation.getLocalPartAndDomain("john.smith@(comment)example.com")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
    result = EmailAddressValidation.getLocalPartAndDomain("john.smith@example.com(comment)")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
  }
  test("both local and domain part has comment") {
    val result = EmailAddressValidation.getLocalPartAndDomain("john.smith(comment)@example.com")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
  }
  test("both local and domain part has comment with nesting") {
    val result = EmailAddressValidation.getLocalPartAndDomain(
      "(anothercomment(comment))john.smith(anothercomment(comment))" +
        "@" +
        "(anothercomment(comment))example.com(anothercomment(comment))")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
  }
  test("with extraneous comments in mid") {
    val result = EmailAddressValidation.getLocalPartAndDomain(
      "((comment))john.(excludeMe)smith((comment))" +
        "@" +
        "((comment))example.com((comment))")
    assert(result.isDefined)
    assert(result.get._1 == "john.smith")
    assert(result.get._2 == "example.com")
  }
  test("negative testing") {
    var result = EmailAddressValidation.getLocalPartAndDomain(
      "john.smith((comment)@example.com")
    assert(result.isEmpty)
    result = EmailAddressValidation.getLocalPartAndDomain(
      "john.smith@example.com(comment))")
    assert(result.isEmpty)
  }
  test("negative testing with screwed bracket") {
    var result = EmailAddressValidation.getLocalPartAndDomain(
      "())))(((abc@gmail.com")
    assert(result.isEmpty)
  }
  test("negative testing with multiple @") {
    var result = EmailAddressValidation.getLocalPartAndDomain(
      "abc@gma@il.com")
    assert(result.isEmpty)
  }
}
