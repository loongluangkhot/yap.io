package io.yap.controllers

import io.yap.models.Message
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller


@Controller
class ChatController constructor(val simpMessagingTemplate: SimpMessagingTemplate) {

    @MessageMapping("/chat/{id}")
    fun chat(@DestinationVariable("id") id: Long, @Payload message: Message): Message {
        simpMessagingTemplate.convertAndSend("/topic/chat/$id", message)
        return message
    }
}