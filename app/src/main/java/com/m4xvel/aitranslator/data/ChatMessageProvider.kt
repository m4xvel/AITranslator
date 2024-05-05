package com.m4xvel.aitranslator.data

import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole

class ChatMessageProvider {
    fun createChatMessages(sourceText: String, translatedText: String, text: String): List<ChatMessage> {
        return mutableListOf(
            ChatMessage(
                role = ChatRole.System,
                content = "You will be provided with a sentence in $sourceText, and your task is to translate it into $translatedText."
            ),
            ChatMessage(
                role = ChatRole.User,
                content = "Translation: $text"
            )
        )
    }
}