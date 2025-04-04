package com.saika.chatleaf.data.repository

import android.util.Log
import com.saika.chatleaf.BuildConfig
import com.saika.chatleaf.data.remote.api.ChatApiService
import com.saika.chatleaf.data.remote.dto.ChatRequestDTO
import com.saika.chatleaf.domain.model.Content
import com.saika.chatleaf.domain.model.Message
import com.saika.chatleaf.domain.model.Part
import com.saika.chatleaf.domain.model.PromptRequest
import com.saika.chatleaf.domain.model.PromptResponse
import com.saika.chatleaf.domain.repository.ChatRepository
import com.saika.chatleaf.domain.util.Resource

class ChatRepositoryImpl(private val api: ChatApiService) : ChatRepository {
    override suspend fun sendMessage(messages: List<Message>): Resource<Message> {
        return try {
            val response = api.chat(ChatRequestDTO(messages = messages))
            val reply = response.choices.first().message
            Log.d("API_RESULT 1 ", reply.toString())
            Resource.Success(reply)
        } catch (e: Exception) {
            Log.d("API_RESULT 2 ", e.toString())
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

//    override suspend fun getPromptResponse(prompt: String): Resource<String> {
//        return try {
//            val response = api.generateContent(
//                PromptRequest(
//                    contents = listOf(mapOf("role" to "user", "parts" to prompt))
//                )
//            )
//            val text = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.get("text") ?: ""
//            Resource.Success(text)
//        } catch (e: Exception) {
//            Log.e("getPromptResponse", e.toString())
//            Resource.Error(e.localizedMessage ?: "Something went wrong")
//        }
//    }

    override suspend fun getPromptResponse(prompt: String): Resource<String> {
        return try {
            val url = "${BuildConfig.BASE_URL}v1beta/models/gemini-2.0-flash:generateContent?key=${BuildConfig.OPENAI_API_KEY}"

            Log.e("Url", url)

            val response = api.generateContent(
                url,
                PromptRequest(
                    contents = listOf(
                        Content(
                            role = "user",
                            parts = listOf(
                                Part(text = prompt)
                            )
                        )
                    )
                )
            )

            val text = response.candidates
                .firstOrNull()
                ?.content?.parts?.firstOrNull()
                ?.text ?: ""

            Resource.Success(text)
        } catch (e: Exception) {
            Log.e("getPromptResponse", e.toString())
            Resource.Error(e.localizedMessage ?: "Something went wrong")
        }
    }

}
