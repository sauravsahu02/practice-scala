package com.interesting.problems

/**
 * Created by Saurav Sahu on 10th June 2021
 */
object EmailAddressValidation {
  // As poer Wiki-page, comments are allowed with parentheses at either end of the local-part;
  // e.g., john.smith(comment)@example.com and (comment)john.smith@example.com
  // are both equivalent to john.smith@example.com.
  type localAndDomainType = Option[(/*local part*/ String, /*domain part*/ String)]
  def getLocalPartAndDomain(emailAddress: String): localAndDomainType = {
    val localAndDomainPart = Array(new StringBuilder, new StringBuilder)
    var counter = 0
    var isDomain = 0
    emailAddress.toCharArray.foreach{
        c: Char => c match {
          case '(' => counter += 1
          case ')' => counter -= 1
          case '@' =>
            if (counter != 0 || isDomain == 1) return None
            isDomain = 1 - isDomain
          case _  => if (counter == 0) localAndDomainPart(isDomain).append(c)
        }
        if (counter < 0) return None
    }
    Option.when(counter == 0)(localAndDomainPart(0).toString(), localAndDomainPart(1).toString())
  }
}
