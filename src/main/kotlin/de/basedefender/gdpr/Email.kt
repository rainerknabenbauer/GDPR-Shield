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
        val fromLine = body.split("\n").first { line -> line.contains("From:") }

        // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
        return fromLine.substring(fromLine.lastIndexOf("<")+1 until fromLine.lastIndexOf(">"))
    }

}