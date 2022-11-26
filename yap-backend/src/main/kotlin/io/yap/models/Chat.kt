package io.yap.models

import java.time.LocalDateTime
import java.util.*

data class Chat(val name: String) {
    val id = UUID.randomUUID().toString()
    val creationDt = LocalDateTime.now()
    private val users = mutableListOf<User>()
    private val messages = mutableListOf<Message>()

    fun admit(user: User): User {
        users.add(user)
        return user
    }

    fun eject(user: User): User {
        users.remove(user)
        return user
    }

    fun hasUser(user: User): Boolean {
        return users.contains(user)
    }

    fun post(message: Message): Message {
        messages.add(message)
        return message
    }

    fun getMessages(): List<Message> {
        return messages
    }
}