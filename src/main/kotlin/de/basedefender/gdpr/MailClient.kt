package de.basedefender.gdpr

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session


@Component
class MailClient(
    private val mailConfig: MailConfig
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun fetchMails(): ArrayList<EmailAdapter> {
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

        emailFolder.close(false)
        store.close()

        return translate(messages)
    }

    private fun translate(messages: Array<Message>): ArrayList<EmailAdapter> {

        val emails = ArrayList<EmailAdapter>()

        for (message in messages)
            emails.add(EmailAdapter(message))

        return emails
    }


}