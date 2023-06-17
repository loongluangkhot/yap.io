package io.yap.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserServiceTest {
    val _userService = UserService()

    @Test
    fun shouldBeAbleToCreateUser() {
        val username = "oliverLeChat"
        val user = _userService.create(username)

        assertEquals(username, user.name)
    }

    @Test
    fun shouldBeAbleToRetriveCreatedUserById() {
        val username = "oliverLeChat"
        val user = _userService.create(username)

        val retrievedUser = _userService.retrieve(user.id)

        assertEquals(user, retrievedUser)
    }
}