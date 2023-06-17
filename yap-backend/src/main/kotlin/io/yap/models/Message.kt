package io.yap.models

import java.time.LocalDateTime
import java.util.UUID

data class Message(val senderId: String, val message: String, val id: String = UUID.randomUUID().toString(), val dt: LocalDateTime = LocalDateTime.now())