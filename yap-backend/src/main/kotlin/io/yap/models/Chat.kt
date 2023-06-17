package io.yap.models

import java.time.LocalDateTime
import java.util.*

data class Chat(val name: String, val id: String = UUID.randomUUID().toString(), val creationDt: LocalDateTime = LocalDateTime.now()) {
    private val users = mutableSetOf<User>()
    private val messages = mutableSetOf<Message>()

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
        return messages.toList()
    }
}