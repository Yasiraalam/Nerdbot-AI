package com.yasir.nerdbot_ai.api

import com.yasir.nerdbot_ai.databinding.ChatleftitemBinding
import com.yasir.nerdbot_ai.models.chat.ChatModel
import com.yasir.nerdbot_ai.models.imageResponse.GenerateImageModel
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {


    @POST("/v1/completions")
   suspend fun getChat(
        @Header("Content-Type") contentType:String,
        @Header("Authorization") authorization:String,
        @Body requestBody:RequestBody
   ): ChatModel

   @POST("/v1/images/generations")
   suspend fun generateImage(): GenerateImageModel
}