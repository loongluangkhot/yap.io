package io.yap.controllers

import io.yap.models.Chat
import io.yap.models.ChatCreationRequest
import io.yap.models.Message
import io.yap.models.User
import io.yap.services.ChatService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("chat")
class ChatController(val simpMessagingTemplate: SimpMessagingTemplate, val chatService: ChatService) {

    private val _logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("")
    fun create(@RequestBody chatCreationRequest: ChatCreationRequest): Chat {
        val user = User(chatCreationRequest.username)
        return chatService.create(user, chatCreationRequest.chatName)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") chatId: String): Chat? {
        return chatService.delete(chatId)
    }

    @PostMapping("/{id}/user")
    fun enter(@PathVariable("id") chatId: String, @RequestBody user: User): Chat {
        val chat = chatService.retrieve(chatId)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat with id $chatId is not found")
        chat.admit(user)
        return chat
    }

    @DeleteMapping("/{id}/user")
    fun leave(@PathVariable("id") chatId: String, @RequestBody user: User): Chat? {
        val chat = chatService.retrieve(chatId)
        chat?.eject(user)
        return chat
    }

    @PostMapping("/{id}/message")
    fun chat(@DestinationVariable("id") chatId: String, @Payload message: Message): Message {
        val chat = chatService.retrieve(chatId)?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat with id $chatId is not found")
        chat.post(message)
        simpMessagingTemplate.convertAndSend("/topic/chat/$chatId", message)
        return message
    }
}