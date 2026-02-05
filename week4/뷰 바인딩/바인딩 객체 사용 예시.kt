package com.example.androidpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.databinding.TestEx1Binding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = TestEx1Binding.inflate(layoutInflater)    //자동으로 만들어지는 클래스의 inflate 함수 호출해 바인딩 객체 얻는다.

        setContentView(binding.root)    //가져온 바인딩 객체의 root 프로퍼티 인수로 전달

        val r = binding.text1   //findByViewId() 대신 바로 .text1로 가져옴 id 필드 변수명 다이렉트 입력
        r.text = "WOW"

    }
}
