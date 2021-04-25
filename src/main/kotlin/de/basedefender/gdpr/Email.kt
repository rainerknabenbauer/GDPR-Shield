package de.basedefender.gdpr

import org.apache.commons.io.IOUtils
import javax.mail.Message
import javax.mail.internet.MimeUtility

class Email(
    private val message: Message
    ) {

    fun getAgencyContact(): String {
        val body = IOUtils.toString(
            MimeUtility.decode(message.inputStream,
                "quoted-printable"),
            "UTF-8"
        )
        // Extract information about the original sender
        val recruiterEmail = body.split("\n").first { line -> line.contains("From:") }
        val clientEmail = body.split("\n").first { line -> line.contains("From:") }

        // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
        return recruiterEmail.substring(recruiterEmail.lastIndexOf("<")+1 until recruiterEmail.lastIndexOf(">"))
    }

}