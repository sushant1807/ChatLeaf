package com.saika.chatleaf.domain.model

data class PromptRequest(
    val contents: List<Content>
)

data class Content(
    val role: String,
    val parts: List<Part>
)

data class Part(
    val text: String
)