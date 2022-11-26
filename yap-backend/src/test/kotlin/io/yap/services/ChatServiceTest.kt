package io.yap.services

import io.yap.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ChatServiceTest {
    val _chatService = ChatService()

    @Test
    fun shouldBeAbleToCreateChatWithTheRightChatName() {
        val user = User("oliverLeChat")
        val chatName = "LaLangueFrancaise"

        val chat = _chatService.create(user, chatName)

        assertEquals(chatName, chat.name)
    }

    @Test
    fun shouldCreateChatWithCreatorInIt() {
        val user = User("oliverLeChat")
        val chatName = "LaLangueFrancaise"

        val chat = _chatService.create(user, chatName)

        assertTrue(chat.hasUser(user))
    }

    @Test
    fun shouldBeAbleToRetrieveCreatedChatById() {
        val user = User("oliverLeChat")
        val chatName = "LaLangueFrancaise"

        val chat = _chatService.create(user, chatName)
        val retrievedChat = _chatService.retrieve(chat.id)

        assertEquals(chat, retrievedChat)
    }
}