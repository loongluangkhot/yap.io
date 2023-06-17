package io.yap.services

import io.yap.models.Chat
import io.yap.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ChatServiceTest {
    val _chatService = ChatService()

    private fun createChat(username: String, chatName: String): Pair<User, Chat> {
        val user = User(username)
        val chat = _chatService.create(user, chatName)
        return Pair(user, chat)
    }

    @Test
    fun shouldBeAbleToCreateChatWithTheRightChatName() {

        val chatName = "LaLangueFrancaise"
        var (_, chat) = createChat("oliverLeChat", chatName)

        assertEquals(chatName, chat.name)
    }

    @Test
    fun shouldCreateChatWithCreatorInIt() {
        val chatName = "LaLangueFrancaise"
        var (user, chat) = createChat("oliverLeChat", chatName)

        assertTrue(chat.hasUser(user))
    }

    @Test
    fun shouldBeAbleToRetrieveCreatedChatById() {
        val chatName = "LaLangueFrancaise"
        var (_, chat) = createChat("oliverLeChat", chatName)
        val retrievedChat = _chatService.retrieve(chat.id)

        assertEquals(chat, retrievedChat)
    }

    @Test
    fun shouldBeAbleToDeleteAChatById() {
        val chatName = "LaLangueFrancaise"
        var (_, chat) = createChat("oliverLeChat", chatName)

        val deletedChat = _chatService.delete(chat.id)

        assertEquals(chat, deletedChat)
        assertNull(_chatService.retrieve(chat.id))

    }
}