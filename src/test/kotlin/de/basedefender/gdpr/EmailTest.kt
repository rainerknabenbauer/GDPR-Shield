package de.basedefender.gdpr

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EmailTest {

    private val request = """
        > From: Laurem Ipsum <laurem.ipsum@agency.com>
        > Subject: Senior Java Freelancer - 100% Remote
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: some.developer@gmail.com
    """

    private val email = Email(request)

    @Test
    fun`successfully extract a recruiter email`() {
        // arrange

        // act
        val actual = email.getAgencyContact()

        // assert
        Assertions.assertEquals("laurem.ipsum@agency.com", actual)
    }

    @Test
    fun`successfully extract a enquirer email`() {
        // arrange

        // act
        val actual = email.getEnquirerContact()

        // assert
        Assertions.assertEquals("some.developer@gmail.com", actual)
    }

}