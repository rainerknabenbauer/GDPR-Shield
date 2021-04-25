package de.basedefender.gdpr.email.value

import de.basedefender.gdpr.email.EmailAdapter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.ArrayList

@Document(collection = "user")
data class User(
    @Id val email: String,
    val incidents: List<Email>
)