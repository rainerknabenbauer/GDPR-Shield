package de.basedefender.gdpr

import org.springframework.stereotype.Component
import java.util.*
import javax.mail.*

@Component
class MailClient(
    private val mailConfig: MailConfig
) {

    fun readMail() {

        val props = Properties()
        props["mail.smtp.starttls.enable"] = mailConfig.starttls
        props["mail.smtp.host"] = mailConfig.host
        props["mail.smtp.port"] = mailConfig.port
        props["mail.smtp.auth"] = "true"


        val session =  Session.getInstance(props,
            object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(mailConfig.user, mailConfig.password)
                }
            })

        val store: Store = session.store
        store.connect(mailConfig.host, null, null)

        val inbox: Folder = store.getFolder("INBOX")
        inbox.open(Folder.READ_WRITE)

        val messages: Array<Message> = inbox.messages

        for (i in messages.indices) {
            println("Message " + (i + 1))
            messages[i].writeTo(System.out)
        }
        inbox.close(false)
        store.close()

    }

}