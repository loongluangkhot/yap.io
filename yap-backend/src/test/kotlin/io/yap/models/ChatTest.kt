package io.yap.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChatTest {
    @Test
    fun shouldBeAbleToAdmitUsers() {
        val user = User("oliverLeChat")
        val chat = Chat("laLangueFrancaise")

        chat.admit(user)

        assertTrue(chat.hasUser(user))
    }

    @Test
    fun shouldBeAbleToEjectUsers() {
        val user = User("oliverLeChat")
        val chat = Chat("laLangueFrancaise")

        chat.admit(user)
        chat.eject(user)

        assertFalse(chat.hasUser(user))
    }

    @Test
    fun shouldBeAbleToPostMessage() {
        val user = User("oliverLeChat")
        val chat = Chat("laLangueFrancaise")
        val message = Message(user, "Bonjour!")

        chat.post(message)

        assertTrue(chat.getMessages().contains(message))
    }

    @Test
    fun ShouldBeAbleToCheckIfUserExists() {
        val user = User("oliverLeChat")
        val user2 = User("ElmoLeChien")
        val chat = Chat("MeowMeow")

        chat.admit(user)

        assertTrue(chat.hasUser(user))
        assertFalse(chat.hasUser(user2))
    }
}