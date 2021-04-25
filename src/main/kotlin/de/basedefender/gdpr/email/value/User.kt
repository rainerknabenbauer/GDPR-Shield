package de.basedefender.gdpr.email.value

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    @Id val email: String,
    val incidents: List<Email>
)