package io.yap.services

import io.yap.models.User

class UserService {
    private val _users = mutableListOf<User>()

    fun create(username: String): User {
        val user = User(username)
        _users.add(user)
        return user
    }

    fun retrieve(userId: String): User? {
        return _users.find { it.id == userId }
    }
}