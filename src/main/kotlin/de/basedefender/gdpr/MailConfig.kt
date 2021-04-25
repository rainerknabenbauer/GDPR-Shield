package de.basedefender.gdpr

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MailConfig {

    @Value("\${spring.mail.host}")
    lateinit var host: String

    @Value("\${spring.mail.user}")
    lateinit var user: String

    @Value("\${spring.mail.username}")
    lateinit var email: String

    @Value("\${spring.mail.password}")
    lateinit var password: String

    @Value("\${spring.mail.properties.mail.smtp.starttls.enabled}")
    var starttls: Boolean = false
}