package de.basedefender.gdpr.email.value

import java.time.LocalDateTime
import java.util.*

data class Email(
    val id: String = UUID.randomUUID().toString(),
    val contact: String,
    val notified: Boolean = false,
    var acknowledged: Boolean = false,
    val date: LocalDateTime = LocalDateTime.now()
) {

    fun setAcknowdleged() {
        this.acknowledged = true;
    }

}
