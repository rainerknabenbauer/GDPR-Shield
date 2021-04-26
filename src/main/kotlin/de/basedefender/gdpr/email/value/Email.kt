package de.basedefender.gdpr.email.value

import java.time.LocalDateTime
import java.util.*

data class Email(
    val id: String = UUID.randomUUID().toString(),
    val contact: String,
    private var notified: Boolean = false,
    private var acknowledged: Boolean = false,
    val date: LocalDateTime = LocalDateTime.now()
) {

    fun isNotified(): Boolean {
        return this.notified
    }

    fun setNotified() {
        this.notified = true
    }

    fun isAcknowledged(): Boolean {
        return this.acknowledged
    }

    fun setAcknowleged() {
        this.acknowledged = true
    }


}
