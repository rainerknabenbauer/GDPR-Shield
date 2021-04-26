package de.basedefender.gdpr.email.value

import de.basedefender.gdpr.email.EmailAdapter

class Users {

    companion object {

        fun fromEmailAdapters(eMails: List<EmailAdapter>): List<User> {
            return eMails
                // get rid of all empty and invalid entries
                .filter { adapter -> adapter.getAgencyContact().isPresent }
                .filter { adapter -> adapter.getUserContact().isPresent }
                .filter { adapter -> adapter.getAgencyContact().get().isNotEmpty() }
                .filter { adapter -> adapter.getUserContact().get().isNotEmpty() }
                // reference all emails to one user
                .groupBy { adapter -> adapter.getUserContact().get() }
                // unwrap the adapter to plane Mail while keeping the association to the user
                .mapValues { adapter -> adapter.value.map { _adapter -> _adapter.toEmail().get() } }
                // bundle everything beautifully in a User object
                .map { adapter -> User(adapter.key, adapter.value) }
        }
    }

}