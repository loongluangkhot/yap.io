package io.yap.models

import lombok.Data

@Data
class Message(val id: Long, val from: String, val message: String, val time: Long)