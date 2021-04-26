package de.basedefender.gdpr.confirmation

import de.basedefender.gdpr.email.value.User
import de.basedefender.gdpr.user.UserRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConfirmationService(
    private val mongoTemplate: MongoTemplate,
    private val userRepository: UserRepository
) {

    fun setConfirmation(incidentId: String): String {
        val pendingNotification = Criteria.where("id").`is`(incidentId)
        val criteria = Criteria("incidents").elemMatch(pendingNotification)
        val query = Query(criteria)

        val user = mongoTemplate.findOne<User>(query)

        return determineStatus(user, incidentId)
    }

    private fun determineStatus(user: User?, incidentId: String): String {
        if (Objects.nonNull(user)) {
            val incident = user!!.incidents.find { incident -> incident.id == incidentId }

            if (Objects.nonNull(incident)) {

                if (incident!!.acknowledged) {
                    return "Already confirmed."
                } else if (!incident.acknowledged) {
                    incident.setAcknowdleged()
                    userRepository.save(user)
                    return "Confirmed!"
                }

            }

        }

        return "Id $incidentId not found."
    }

}
