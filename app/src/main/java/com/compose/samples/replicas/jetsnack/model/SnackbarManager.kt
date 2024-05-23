package com.compose.samples.replicas.jetsnack.model

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class Message(
    val id: Long,
    @StringRes val messageId: Int
)

/**
 * Manages snackbar messages to show on the screen
 */
object SnackbarManager {
    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>>
        get() = _messages.asStateFlow()

    fun showMessage(@StringRes messageTextId: Int) {
        _messages.update { currentMessages ->
            currentMessages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageTextId
            )
        }
    }

    fun setMessageShow(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }
}