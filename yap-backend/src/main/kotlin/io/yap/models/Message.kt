package io.yap.models

import java.time.LocalDateTime
import java.util.UUID

data class Message(val sender: User, val message: String) {
    val id = UUID.randomUUID().toString()
    val dt = LocalDateTime.now()
}