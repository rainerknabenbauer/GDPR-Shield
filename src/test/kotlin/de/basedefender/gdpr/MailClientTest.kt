package de.basedefender.gdpr

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MailClientTest(
    @Autowired val mailClient: MailClient
) {

    @Test
    fun `successfully connect mail client`() {
        // arrange

        // act
        mailClient.readMail()

        // assert
    }

}