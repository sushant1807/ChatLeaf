package com.saika.chatleaf.data.remote.dto

import com.saika.chatleaf.domain.model.Message

data class ChatRequestDTO(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

data class ChatResponseDTO(val choices: List<ChoiceDTO>) {
    data class ChoiceDTO(val message: Message)
}