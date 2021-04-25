package de.basedefender.gdpr

import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.MimeUtility


@Component
class MailClient(
    private val mailConfig: MailConfig
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun readMail() {
        val properties = Properties()

        properties["mail.pop3.host"] = mailConfig.host
        properties["mail.pop3.port"] = mailConfig.portPop3
        properties["mail.pop3.starttls.enable"] = "true"
        val emailSession = Session.getDefaultInstance(properties)

        val store = emailSession.getStore("pop3s")
        store.connect(mailConfig.host, mailConfig.user, mailConfig.password)

        val emailFolder = store.getFolder("INBOX")
        emailFolder.open(Folder.READ_ONLY)

        val messages = emailFolder.messages
        messages.forEach {message -> printMessageDetails(message) }

        emailFolder.close(false)
        store.close()

    }

    private fun printMessageDetails(message: Message) {

        val body = IOUtils.toString(
            MimeUtility.decode(message.inputStream,
                "quoted-printable"),
            "UTF-8"
        )
        val fromLine = body.split("\n").first { line -> line.contains("From:") }

        println("from line: $fromLine")
    }



}