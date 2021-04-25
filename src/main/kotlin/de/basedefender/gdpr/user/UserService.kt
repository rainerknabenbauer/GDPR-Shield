package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.MailClient
import de.basedefender.gdpr.email.value.User
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val mongoTemplate: MongoTemplate,
    private val mailClient: MailClient
) {

    fun addEmails(users : List<User>) {
        users.forEach { user -> run {
            println("Saving user ${user.email}")
            userRepository.save(user)
        }}
    }

    /**
     * Find all users with pending notifications.
     */
    fun getPendingNotifications(): List<User> {
        val pendingNotification = Criteria.where("notified").`is`(false)
        val criteria = Criteria("incidents").elemMatch(pendingNotification)
        val query = Query(criteria)
        return mongoTemplate.find(query)
    }

    fun sendGdprNotifications(users: List<User>) {
        for (user in users) {
            user.incidents
                .filter { incident -> !incident.notified }
                .forEach { incident -> run {
                    val email = mailClient.createGdprEmail(user)
                    mailClient.send(email, incident)
                } }
        }
    }

}
