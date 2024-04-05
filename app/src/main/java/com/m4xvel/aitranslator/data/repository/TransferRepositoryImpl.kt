package com.m4xvel.aitranslator.data.repository

import com.m4xvel.aitranslator.data.OpenAIClient
import com.m4xvel.aitranslator.domain.TransferRepository

internal class TransferRepositoryImpl(
    private val openAIClient: OpenAIClient
) : TransferRepository {

    override suspend fun getTransfer(
        sourceText: String,
        translatedText: String,
        text: String
    ): String? {
        return openAIClient.openAI.chatCompletion(
            openAIClient.getTransfer(
                sourceText,
                translatedText,
                text
            )
        ).choices.first().message.content
    }
}
