package de.basedefender.gdpr

import org.apache.commons.io.IOUtils
import javax.mail.Message
import javax.mail.internet.MimeUtility

class Email(
    private val message: Message
    ) {

    fun getAgencyContact() {
        val body = IOUtils.toString(
            MimeUtility.decode(message.inputStream,
                "quoted-printable"),
            "UTF-8"
        )
        val fromLine = body.split("\n").first { line -> line.contains("From:") }

        println("from line: $fromLine")

        // Cut the eMail part out of the From line, example:      "> From: Lorem Ipsum <lorem.ipsum@gmail.de>"
        val email = fromLine.substring(fromLine.lastIndexOf("<")+1 until fromLine.lastIndexOf(">"))

        println("extracted email: $email")
    }

}