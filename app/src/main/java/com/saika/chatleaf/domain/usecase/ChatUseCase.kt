package com.saika.chatleaf.domain.usecase

import com.saika.chatleaf.domain.model.Message
import com.saika.chatleaf.domain.repository.ChatRepository
import com.saika.chatleaf.domain.util.Resource

class ChatUseCase( private val repository: ChatRepository) {
//    suspend operator fun invoke(messages: List<Message>): Resource<Message> {
//        return repository.sendMessage(messages)
//    }

    suspend operator fun invoke(prompt: String): Resource<String> {
        return repository.getPromptResponse(prompt)
    }
}