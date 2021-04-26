package de.basedefender.gdpr.confirmation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfirmationController(
    private val confirmationService: ConfirmationService
) {

    @GetMapping(path = ["/incidents/{incidentId}"])
    fun waiting(@PathVariable incidentId: String): ResponseEntity<String> {
        val status = confirmationService.setConfirmation(incidentId)
        return ResponseEntity.ok().body(status)
    }


}