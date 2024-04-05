package com.m4xvel.aitranslator.data

import android.util.Log
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.chat.chatCompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.m4xvel.aitranslator.BuildConfig

internal class OpenAIClient {

    private val token = BuildConfig.OPENAI_API_KEY
    val openAI = OpenAI(token)

    private val modelId = ModelId("gpt-3.5-turbo")

    private val chatMessages = mutableListOf(
        ChatMessage(
            role = ChatRole.System,
            content = "You will be provided with a sentence in English, and your task is to translate it into Russian."
        ),
        ChatMessage(
            role = ChatRole.User,
            content = "Amongst the greenery and flowers of the park, one can find refuge from the hustle and bustle of city life and enjoy a moment of tranquility and peace."
        )
    )

    val request = chatCompletionRequest {
        model = modelId
        messages = chatMessages
        temperature = 0.7
        topP = 1.0
        maxTokens = 64
    }

}
