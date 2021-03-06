package de.basedefender.gdpr.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MailConfig {

    @Value("\${mail.host}")
    lateinit var host: String

    @Value("\${mail.port.smtp}")
    lateinit var portSmtp: String

    @Value("\${mail.port.pop3}")
    lateinit var portPop3: String

    @Value("\${mail.user}")
    lateinit var user: String

    @Value("\${mail.email}")
    lateinit var email: String

    @Value("\${mail.password}")
    lateinit var password: String

}