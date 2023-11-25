package com.yasir.nerdbot_ai.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yasir.nerdbot_ai.R
import com.yasir.nerdbot_ai.adapters.MessageAdapter
import com.yasir.nerdbot_ai.api.ApiInterface
import com.yasir.nerdbot_ai.api.ApiUtilities
import com.yasir.nerdbot_ai.databinding.ActivityChatBinding
import com.yasir.nerdbot_ai.models.request.ChatRequest
import com.yasir.nerdbot_ai.models.request.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private var list = ArrayList<MessageModel>()
    private lateinit var adapter: MessageAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,SplashActivity::class.java))
            finish()
        }
        mLayoutManager = LinearLayoutManager(this)
        //for scroller
        mLayoutManager.stackFromEnd = true
        adapter = MessageAdapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = mLayoutManager

        binding.sendbtn.setOnClickListener {
            if (binding.userMsg.text!!.isEmpty()) {
                Toast.makeText(this, "Please Ask your Question", Toast.LENGTH_SHORT).show()
            } else {
                callApi()
            }
        }
    }

    private fun callApi() {
        list.add(MessageModel(true, false, binding.userMsg.text.toString()))
        adapter.notifyItemInserted(list.size - 1)
        binding.recyclerView.recycledViewPool.clear()
        binding.recyclerView.smoothScrollToPosition(list.size - 1)

        val apiInterface = ApiUtilities.getApiInterface()
        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            Gson().toJson(
                ChatRequest(
                    250,
                     "text-davinci-003",
                    binding.userMsg.text.toString(),
                    0.7
                     )
                )
            )
        val contentType = "application/json"
        val authorization = "Bearer ${Utils.API_KEY}"
        //hit krain gai
       lifecycleScope.launch(Dispatchers.IO) {

           try {
               val response = apiInterface.getChat(
                   contentType,
                   authorization,
                   requestBody
               )
               val textresponse = response.choices.first().text
               list.add(
                   MessageModel(false,false, textresponse)
               )
               withContext(Dispatchers.Main){
                   adapter.notifyItemInserted(list.size - 1)
                   binding.recyclerView.recycledViewPool.clear()
                   binding.recyclerView.smoothScrollToPosition(list.size - 1)
               }
               binding.userMsg.text!!.clear()

           }catch (e:Exception){
               withContext(Dispatchers.Main){
                   Toast.makeText(this@ChatActivity, e.message, Toast.LENGTH_SHORT).show()
               }
           }



       }
    }
}