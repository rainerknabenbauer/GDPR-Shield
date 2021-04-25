package de.basedefender.gdpr

import de.basedefender.gdpr.email.EmailAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EmailAdapterTest {

    private val request = """
        > From: Laurem Ipsum <laurem.ipsum@agency.com>
        > Subject: Senior Java Freelancer - 100% Remote
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: some.developer@gmail.com
    """

    @Test
    fun`successfully extract a recruiter email`() {
        // arrange
        val email = EmailAdapter(request)

        // act
        val actual = email.getAgencyContact()

        // assert
        Assertions.assertEquals("laurem.ipsum@agency.com", actual.get())
    }

    @Test
    fun`failed to extract a recruiter email`() {
        // arrange
        val email = EmailAdapter("")

        // act
        val actual = email.getAgencyContact()

        // assert
        Assertions.assertTrue(actual.isEmpty)
    }

    @Test
    fun`successfully extract a users email`() {
        // arrange
        val email = EmailAdapter(request)

        // act
        val actual = email.getUserContact()

        // assert
        Assertions.assertEquals("some.developer@gmail.com", actual.get())
    }

    @Test
    fun`failed to extract a users email`() {
        // arrange
        val email = EmailAdapter("")

        // act
        val actual = email.getUserContact()

        // assert
        Assertions.assertTrue(actual.isEmpty)
    }

    @Test
    fun`successful convert to email`() {
        // arrange
        val email = EmailAdapter(request)

        // act
        val actual = email.toEmail()

        // assert
        Assertions.assertTrue(actual.isPresent)
        Assertions.assertEquals("laurem.ipsum@agency.com", actual.get().contact)
    }

}