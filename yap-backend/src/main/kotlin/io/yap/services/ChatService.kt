package io.yap.services

import io.yap.models.Chat
import io.yap.models.User
import org.springframework.stereotype.Service

@Service
class ChatService {
    private val _chats = mutableListOf<Chat>()

    fun create(user: User, chatName: String): Chat {
        val chat = Chat(chatName)
        chat.admit(user)
        _chats.add(chat)
        return chat
    }

    fun retrieve(chatId: String): Chat? {
        return _chats.find { it.id == chatId }
    }

    fun delete(chatId: String): Chat? {
        val deletedChat = retrieve(chatId)
        if (deletedChat != null) {
            _chats.remove(deletedChat)
        }
        return deletedChat
    }


}