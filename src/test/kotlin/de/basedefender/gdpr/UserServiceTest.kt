package de.basedefender.gdpr

import com.ninjasquad.springmockk.MockkBean
import de.basedefender.gdpr.email.EmailAdapter
import de.basedefender.gdpr.user.UserRepository
import de.basedefender.gdpr.user.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceTest(
    @Autowired private val userService: UserService
) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    private val request = """
        > From: Laurem Ipsum <laurem.ipsum@agency.com>
        > Subject: Senior Java Freelancer - 100% Remote
        > Date: 23. April 2021 at 12:07:46 CEST
        > To: some.developer@gmail.com
    """

    @Test
    fun `group by user email`() {
        // arrange
        val emailAdapters = ArrayList<EmailAdapter>()
        emailAdapters.add(EmailAdapter(request))
        emailAdapters.add(EmailAdapter(request))
        emailAdapters.add(EmailAdapter(""))


        // act
        userService.upsertAll(emailAdapters)

        // assert

    }

}