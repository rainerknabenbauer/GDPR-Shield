package de.basedefender.gdpr

import org.apache.commons.io.IOUtils
import javax.mail.Message
import javax.mail.internet.MimeUtility

class Email {

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

    fun getAgencyContact(): String {
        // Extract information about the original sender
        val recruiter = text.split("\n").first { line -> line.contains("From:") }

        // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
        return recruiter.substring(
                recruiter.lastIndexOf("<")+1 until recruiter.lastIndexOf(">")).trim()
    }

    fun getEnquirerContact(): String {
        // Extract information about the original sender
        val enquirer = text.split("\n").first { line -> line.contains("To:") }

        // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
        return enquirer.substring(enquirer.lastIndexOf(":")+1).trim()
    }

}