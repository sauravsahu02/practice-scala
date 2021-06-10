/**
 * Created by Saurav Sahu on 10th June 2021
 */
object EmailAddressValidation {
  // As poer Wiki-page, comments are allowed with parentheses at either end of the local-part;
  // e.g., john.smith(comment)@example.com and (comment)john.smith@example.com
  // are both equivalent to john.smith@example.com.
  def getLocalPartAndDomain(emailAddress: String): Option[(/*local part*/ String, /*domain part*/ String)] = {
    val localAndDomainPart = Array(new StringBuilder, new StringBuilder)
    var counter = 0
    var isDomain = 0
    var index = 0
    var openBracketPosition = 0
    emailAddress.toCharArray.foreach{
      c: Char => c match {
        case '(' =>
          counter += 1
          if (counter == 1) openBracketPosition = index
        case ')' =>
          counter -= 1
          if (index != emailAddress.indexOf('@') - 1 && openBracketPosition != 0) {
            return None
          }
        case '@' =>
          if (counter != 0 || isDomain == 1) return None
          isDomain = 1 - isDomain
        case _  => if (counter == 0) localAndDomainPart(isDomain).append(c)
      }
        index += 1
        if (counter < 0) return None
    }
    Option.when(counter == 0)(localAndDomainPart(0).toString(), localAndDomainPart(1).toString())
  }
}