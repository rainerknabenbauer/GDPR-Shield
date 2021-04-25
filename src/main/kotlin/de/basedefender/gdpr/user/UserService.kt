package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.value.User
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val mongoTemplate: MongoTemplate
) {

    fun addEmails(users : List<User>) {
        users.forEach { user -> run {
            println("Saving user ${user.email}")
            userRepository.save(user)
        }}
    }

    fun getPendingNotifications() {
        // elem match
        val pendingNotification = Criteria.where("notified").`is`(false)
        val criteria = Criteria("incidents").elemMatch(pendingNotification)
        val query = Query(criteria)
        val users = mongoTemplate.find<User>(query)

        users.forEach { user -> println(user.email) }

    }

}
