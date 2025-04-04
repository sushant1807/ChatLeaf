package com.saika.chatleaf.domain.model

data class PromptResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: Content
)
