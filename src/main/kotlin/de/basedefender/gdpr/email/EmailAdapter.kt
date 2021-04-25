package de.basedefender.gdpr.email

import de.basedefender.gdpr.email.value.Email
import org.apache.commons.io.IOUtils
import java.util.*
import javax.mail.Message
import javax.mail.internet.MimeUtility
import kotlin.NoSuchElementException

class EmailAdapter {

    private val text: String

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
            val recruiter = text.split("\n").first { line -> line.contains("From:") }

            // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
            Optional.of(recruiter.substring(
                recruiter.lastIndexOf("<")+1 until recruiter.lastIndexOf(">")).trim())
        } catch (ex: NoSuchElementException) {
            Optional.empty<String>()
        }
    }

    fun getUserContact(): Optional<String> {
        return try {
            // Extract contact information for the person being bothered
            val user = text.split("\n").first { line -> line.contains("To:") }

            // Cut the eMail part out of the From line, example:      "> To: lorem.ipsum@gmail.de"
            return Optional.of(user.substring(user.lastIndexOf(":")+1).trim())
        } catch (ex: NoSuchElementException) {
            Optional.empty<String>()
        }
    }

}