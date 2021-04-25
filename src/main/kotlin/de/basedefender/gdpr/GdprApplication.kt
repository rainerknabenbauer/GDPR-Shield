package de.basedefender.gdpr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GdprApplication

fun main(args: Array<String>) {
	runApplication<GdprApplication>(*args)
}
