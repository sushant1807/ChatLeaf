package com.saika.chatleaf.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saika.chatleaf.domain.usecase.ChatUseCase
import com.saika.chatleaf.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    var uiState by mutableStateOf<Resource<String>>(Resource.Success("Ask me anything"))
        private set

    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            uiState = Resource.Loading

            val result = chatUseCase(prompt)
            uiState = result
        }
    }
}

