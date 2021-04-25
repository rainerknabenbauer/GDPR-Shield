package de.basedefender.gdpr

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MailConfig {

    @Value("\${mail.host}")
    lateinit var host: String

    @Value("\${mail.port.pop3}")
    lateinit var port: String

    @Value("\${mail.user}")
    lateinit var user: String

    @Value("\${mail.email}")
    lateinit var email: String

    @Value("\${mail.password}")
    lateinit var password: String

}