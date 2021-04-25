package de.basedefender.gdpr.user

import de.basedefender.gdpr.email.EmailAdapter
import de.basedefender.gdpr.email.value.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) {

    fun upsertAll(eMails: ArrayList<EmailAdapter>) {

        val mailsByUser = eMails
            .filter { adapter -> adapter.getAgencyContact().isPresent }
            .filter { adapter -> adapter.getUserContact().isPresent }
            .groupBy { adapter -> adapter.getUserContact().get() }
            .mapValues { adapter -> adapter.value.map { _adapter -> _adapter.toEmail().get() } }

        upsertAll(mailsByUser)

    }

    fun upsertAll(mailsByUser: Map<String, List<Email>>) {

    }

}
