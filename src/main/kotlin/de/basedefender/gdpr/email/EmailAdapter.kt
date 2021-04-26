package de.basedefender.gdpr.email

import de.basedefender.gdpr.email.value.Email
import org.apache.commons.io.IOUtils
import java.util.*
import java.util.regex.Pattern
import javax.mail.Message
import javax.mail.internet.MimeUtility

/**
 * Extracts from IMAP and transforms into internal eMail representation.
 */
class EmailAdapter {

    private val text: String

    private val eMailPattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")

    constructor(message: Message) {
        this.text = IOUtils.toString(
            MimeUtility.decode(message.inputStream,
                "quoted-printable"),
            "UTF-8"
        )
    }

    constructor(message: String) {
        this.text = message
    }

    fun toEmail(): Optional<Email> {
        return getAgencyContact().map { contact -> Email(contact = contact) }
    }

    fun getAgencyContact(): Optional<String> {

        return try {
            // Extract contact information for the original sender
            val recruiter = text.split("\n").first { line -> line.toLowerCase().contains("from:") }

            // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
            Optional.of(extractEmail(recruiter))
        } catch (ex: NoSuchElementException) {
            Optional.empty<String>()
        }
    }

    fun getUserContact(): Optional<String> {
        return try {
            // Extract contact information for the person being bothered
            val user = text.split("\n").first { line -> line.toLowerCase().contains("to:") }

            // Cut the eMail part out of the From line, example:      "> To: lorem.ipsum@gmail.de"
            return Optional.of(extractEmail(user))
        } catch (ex: NoSuchElementException) {
            Optional.empty<String>()
        }
    }

    private fun extractEmail(line: String): String {
        val matcher = eMailPattern.matcher(line)

        var email = ""

        // find first eMail
        if (matcher.find()) matcher.group().toLowerCase()

        // check if there is more than one eMail address - if so, invalidate
        if (matcher.find()) email = ""

        return email
    }

}