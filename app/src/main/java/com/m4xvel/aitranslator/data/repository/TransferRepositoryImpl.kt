package com.m4xvel.aitranslator.data.repository

import android.util.Log
import com.m4xvel.aitranslator.data.OpenAIClient
import com.m4xvel.aitranslator.domain.TransferRepository

internal class TransferRepositoryImpl(
    private val openAIClient: OpenAIClient
) : TransferRepository {

    override suspend fun getTransfer(): String? {
        return openAIClient.openAI.chatCompletion(openAIClient.request).choices.first().message.content
    }
}
