package io.yap.models

import java.util.*

data class User(val name: String, val id: String = UUID.randomUUID().toString())