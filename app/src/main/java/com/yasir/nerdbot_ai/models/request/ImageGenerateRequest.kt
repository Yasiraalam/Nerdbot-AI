package com.yasir.nerdbot_ai.models.request

data class ImageGenerateRequest(
    val n: Int,
    val prompt: String,
    val size: String
)