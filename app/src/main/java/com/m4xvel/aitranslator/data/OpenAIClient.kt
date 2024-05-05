package com.m4xvel.aitranslator.data

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.chatCompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.aallam.openai.client.OpenAIHost
import com.m4xvel.aitranslator.BuildConfig

internal class OpenAIClient(
    private val chatMessageProvider: ChatMessageProvider
) {

    private val token = BuildConfig.OPENAI_API_KEY
    private val host = OpenAIHost(
        baseUrl = "https://api.proxyapi.ru/openai/v1/chat"
    )
    private val config = OpenAIConfig(
        host = host,
        token = token
    )
    val openAI = OpenAI(config)

    private val modelId = ModelId("gpt-3.5-turbo")

    fun getTransfer(
        sourceText: String,
        translatedText: String,
        text: String
    ): ChatCompletionRequest {
        val chatMessages = chatMessageProvider.createChatMessages(sourceText, translatedText, text)
        return chatCompletionRequest {
            model = modelId
            messages = chatMessages
            temperature = 0.1
            maxTokens = 128
        }
    }
}
