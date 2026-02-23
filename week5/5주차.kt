package com.example.kakao_talk_password_check

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kakao_talk_password_check.databinding.KakaoPasswordBinding
import com.example.kakao_talk_password_check.databinding.KeyBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = KeyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
