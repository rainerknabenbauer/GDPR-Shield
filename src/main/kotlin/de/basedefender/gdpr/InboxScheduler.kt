package de.basedefender.gdpr

import de.basedefender.gdpr.email.MailClient
import de.basedefender.gdpr.user.UserService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class InboxScheduler(
    private val mailClient: MailClient,
    private val userService: UserService
) {

    @Scheduled(fixedDelay = 10000)
    fun fetchEmails() {
        val eMails = mailClient.fetchMails()
        userService.upsertAll(eMails)
    }

}