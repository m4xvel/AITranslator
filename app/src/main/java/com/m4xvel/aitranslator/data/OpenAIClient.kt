package com.m4xvel.aitranslator.data

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.chatCompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.m4xvel.aitranslator.BuildConfig

internal class OpenAIClient(
    private val chatMessageProvider: ChatMessageProvider
) {

    private val token = BuildConfig.OPENAI_API_KEY
    val openAI = OpenAI(token)

    private val modelId = ModelId("gpt-3.5-turbo")

    fun getTransfer(sourceText: String, translatedText: String, text: String): ChatCompletionRequest {
        val chatMessages = chatMessageProvider.createChatMessages(sourceText, translatedText, text)
        return chatCompletionRequest {
            model = modelId
            messages = chatMessages
            temperature = 0.7
            topP = 1.0
            maxTokens = 64
        }
    }
}
