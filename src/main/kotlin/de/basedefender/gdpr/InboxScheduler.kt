package de.basedefender.gdpr

import de.basedefender.gdpr.email.MailClient
import de.basedefender.gdpr.email.value.Users
import de.basedefender.gdpr.user.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class InboxScheduler(
    private val mailClient: MailClient,
    private val userService: UserService
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${scheduler.active}")
    private var isActive: Boolean = false

    /**
     * Regularly fetches eMails from the inbox and stores them in the database.
     */
    @Scheduled(fixedDelay = 300000)
    fun fetchEmails() {
        if (isActive) {
            log.info("Fetching eMails ...")
            val eMails = mailClient.fetchMails()
            log.info("Fetched ${eMails.size} new emails.")
            userService.addEmails(Users.fromEmailAdapters(eMails))
        }
    }

    /**
     * Send 'cease and delete' notifications to companies.
     */
    @Scheduled(fixedDelay = 600000)
    fun sendEmails() {
        if (isActive) {
            val users = userService.getPendingNotifications()
            log.info("Found ${users.size} users with pending notifications.")

            if (users.isNotEmpty()) {
                userService.sendGdprNotifications(users)
                log.info("Sent 'cease and delete' notifications.")
            }
        }
    }

}