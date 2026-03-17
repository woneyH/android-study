package com.practice.androidbookpractice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.androidbookpractice.databinding.ActivityGeminiFragmentTestBinding

class FragmentTestActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityGeminiFragmentTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickBtn()
    }
    
    private fun clickBtn() {
        binding.redBtn.setOnClickListener {
            val myRedFragment = RedFragment()

            myRedFragment.call = object : RedFragment.CallSign {
                override fun call() {
                    Toast.makeText(this@FragmentTestActivity,"버튼 클릭함!!",Toast.LENGTH_SHORT).show()
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, myRedFragment)
                .commit()
        }

        //Blue Fragment는 별도의 기능 없습니다.
        binding.blueBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BlueFragment())
                .commit()
        }
    }
}
