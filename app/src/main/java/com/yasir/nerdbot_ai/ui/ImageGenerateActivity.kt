package com.yasir.nerdbot_ai.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yasir.nerdbot_ai.R
import com.yasir.nerdbot_ai.databinding.ActivityImageGenerateBinding

class ImageGenerateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageGenerateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageGenerateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,SplashActivity::class.java))
            finish()
        }

    }
}