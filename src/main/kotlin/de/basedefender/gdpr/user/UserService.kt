package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.value.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) {

    fun addEmails(users : List<User>) {
        users.forEach { user -> userRepository.save(user)}
    }

}
