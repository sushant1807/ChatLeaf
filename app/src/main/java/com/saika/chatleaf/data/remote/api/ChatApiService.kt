package com.saika.chatleaf.data.remote.api

import com.saika.chatleaf.BuildConfig
import com.saika.chatleaf.data.remote.dto.ChatRequestDTO
import com.saika.chatleaf.data.remote.dto.ChatResponseDTO
import com.saika.chatleaf.domain.model.PromptRequest
import com.saika.chatleaf.domain.model.PromptResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface ChatApiService {

    @POST("v1/chat/completions")
    suspend fun chat(@Body request: ChatRequestDTO): ChatResponseDTO

    @POST
    suspend fun generateContent(
        @Url url: String,
        @Body prompt: PromptRequest
    ): PromptResponse

}