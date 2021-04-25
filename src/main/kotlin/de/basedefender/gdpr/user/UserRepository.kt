package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.value.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {

}