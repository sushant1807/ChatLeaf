package com.saika.chatleaf.domain.repository

import com.saika.chatleaf.domain.model.Message
import com.saika.chatleaf.domain.util.Resource

interface ChatRepository {

    suspend fun sendMessage(messages: List<Message>): Resource<Message>

    suspend fun getPromptResponse(prompt: String): Resource<String>
}