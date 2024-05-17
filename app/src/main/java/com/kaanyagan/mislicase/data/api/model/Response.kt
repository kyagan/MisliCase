package com.kaanyagan.mislicase.data.api.model

data class Response<T>(
    val success: Boolean,
    val `data`: T
)