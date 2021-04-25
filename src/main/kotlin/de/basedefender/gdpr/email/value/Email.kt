package de.basedefender.gdpr.email.value

import java.time.LocalDateTime
import java.util.*

data class Email(
    val id: UUID = UUID.randomUUID(),
    val contact: String,
    val notified: Boolean = false,
    val acknowledged: Boolean = false,
    val date: LocalDateTime = LocalDateTime.now()
)
