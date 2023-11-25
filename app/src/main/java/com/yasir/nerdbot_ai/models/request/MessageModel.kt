package com.yasir.nerdbot_ai.models.request

data class MessageModel(
    var isUser :Boolean,
    var isImage:Boolean,
    var message:String
)