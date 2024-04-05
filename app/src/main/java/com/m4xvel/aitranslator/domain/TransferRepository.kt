package com.m4xvel.aitranslator.domain

interface TransferRepository {
    suspend fun getTransfer(): String?
}
