package de.basedefender.gdpr.email

import de.basedefender.gdpr.email.value.Email
import de.basedefender.gdpr.email.value.OutboundEmail
import de.basedefender.gdpr.email.value.User
import org.springframework.stereotype.Component
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Component
class MailClient(
    private val mailConfig: MailConfig
) {

    /*      INBOUND      */

    fun fetchMails(): ArrayList<EmailAdapter> {
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.host"] = mailConfig.host
        props["mail.smtp.port"] = mailConfig.portSmtp

        val emailSession = Session.getDefaultInstance(props)

        val store = emailSession.getStore("imaps")
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

    /*      OUTBOUND      */

    fun createGdprEmail(user: User, incident: Email): OutboundEmail {
        val title = "Erasure requests as per Art. 17 GDPR"
        val content = this::class.java.getResource("/templates/gdpr-notification.html")
            .readText(Charsets.UTF_8)
            .replace("{{EMAIL}}", user.email)
            .replace("{{TRANSACTION_ID}}", incident.id.toString())

        return OutboundEmail(title, content)
    }

    fun send(outboundEmail: OutboundEmail, email: Email) {
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.host"] = mailConfig.host
        props["mail.smtp.port"] = mailConfig.portSmtp

        val session: Session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(mailConfig.email, mailConfig.password)
            }
        })
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(mailConfig.email, false))

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.contact))
        msg.subject = outboundEmail.title
        msg.setContent(outboundEmail.content, "text/html; charset=UTF-8")
        msg.sentDate = Date()

        Transport.send(msg)
    }

}