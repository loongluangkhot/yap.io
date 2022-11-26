package io.yap.services

import io.yap.models.Chat
import io.yap.models.User

class ChatService {
    val _chats = mutableListOf<Chat>()

    fun create(user: User, chatName: String): Chat {
        val chat = Chat(chatName)
        chat.admit(user)
        _chats.add(chat)
        return chat
    }

    fun retrieve(chatId: String): Chat? {
        return _chats.find { it.id == chatId }
    }
}