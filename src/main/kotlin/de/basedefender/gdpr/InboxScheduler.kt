package de.basedefender.gdpr

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled

class InboxScheduler(
    private val mailClient: MailClient
) {

    @Async
    @Scheduled(fixedDelay = 10000)
    fun fetchEmails() {
        val eMails = mailClient.fetchMails()
    }

}