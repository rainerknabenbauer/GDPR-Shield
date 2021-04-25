package de.basedefender.gdpr.email

import org.springframework.stereotype.Component
import java.util.*
import javax.mail.Flags
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session


@Component
class MailClient(
    private val mailConfig: MailConfig
) {

    fun fetchMails(): ArrayList<EmailAdapter> {
        val properties = Properties()

        properties["mail.pop3.host"] = mailConfig.host
        properties["mail.pop3.port"] = mailConfig.portPop3
        properties["mail.pop3.starttls.enable"] = "true"
        val emailSession = Session.getDefaultInstance(properties)

        val store = emailSession.getStore("pop3s")
        store.connect(mailConfig.host, mailConfig.user, mailConfig.password)

        val emailFolder = store.getFolder("INBOX")
        emailFolder.open(Folder.READ_WRITE)

        val messages = translate(emailFolder.messages)
        deleteMessages(emailFolder.messages)

        emailFolder.close(true)
        store.close()

        return messages
    }

    private fun deleteMessages(messages: Array<Message>) {
        messages.forEach { message -> message.setFlag(Flags.Flag.DELETED, true); }
    }

    private fun translate(messages: Array<Message>): ArrayList<EmailAdapter> {

        val emails = ArrayList<EmailAdapter>()

        for (message in messages)
            emails.add(EmailAdapter(message))

        return emails
    }


}