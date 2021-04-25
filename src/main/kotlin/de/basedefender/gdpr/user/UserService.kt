package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.value.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun addEmails(users : List<User>) {
        users.forEach { user -> run {
            println("Saving user ${user.email}")
            userRepository.save(user)
        }}
    }

}
