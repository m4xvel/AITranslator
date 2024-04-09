package com.m4xvel.aitranslator.domain.repository

interface TransferRepository {
    suspend fun getTransfer(sourceText: String, translatedText: String, text: String): String?
}
