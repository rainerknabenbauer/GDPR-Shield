package de.basedefender.gdpr.email.value

import de.basedefender.gdpr.email.EmailAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UsersTest {

    private val first_request = """
        > From: Laurem Ipsum <laurem.ipsum@agency.com>
        > Subject: Senior Java Freelancer - 100% Remote
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: some.developer@gmail.com
    """

    private val second_request = """
        > From: Weil Neil <weil.neil@agency.de>
        > Subject: Junior Java Freelancer - 100% on-site
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: another.developer@yahoo.com
    """

    private val third_request = """
        > From: Laurem Ipsum <laurem.ipsum@agency.com>
        > Subject: Junior Java Freelancer - 100% on-site
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: another.developer@yahoo.com
    """

    @Test
    fun `successfully create Users from MailAdapters`() {
        // arrange
        val emailAdapters = ArrayList<EmailAdapter>()
        emailAdapters.add(EmailAdapter(first_request))
        emailAdapters.add(EmailAdapter(second_request))

        // act
        val users = Users.fromEmailAdapters(emailAdapters)

        // assert
        Assertions.assertEquals(2, users.size)
        Assertions.assertEquals("some.developer@gmail.com", users[0].email)
        Assertions.assertEquals("laurem.ipsum@agency.com", users[0].incidents[0].contact)
        Assertions.assertEquals("another.developer@yahoo.com", users[1].email)
        Assertions.assertEquals("weil.neil@agency.de", users[1].incidents[0].contact)
    }

    @Test
    fun `successfully create multiple emails for same Users from MailAdapters`() {
        // arrange
        val emailAdapters = ArrayList<EmailAdapter>()
        emailAdapters.add(EmailAdapter(first_request))
        emailAdapters.add(EmailAdapter(second_request))
        emailAdapters.add(EmailAdapter(third_request))

        // act
        val users = Users.fromEmailAdapters(emailAdapters)

        // assert
        Assertions.assertEquals(2, users.size)
        Assertions.assertEquals("some.developer@gmail.com", users[0].email)
        Assertions.assertEquals("laurem.ipsum@agency.com", users[0].incidents[0].contact)

        Assertions.assertEquals("another.developer@yahoo.com", users[1].email)
        Assertions.assertEquals("weil.neil@agency.de", users[1].incidents[0].contact)
        Assertions.assertEquals("laurem.ipsum@agency.com", users[1].incidents[1].contact)
    }

}