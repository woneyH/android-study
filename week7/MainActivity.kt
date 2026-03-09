package com.practice.stop_watch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.practice.stop_watch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var initTime = 0L
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.timer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.timer.start()

            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        binding.stopButton.setOnClickListener {
            pauseTime = binding.timer.base - SystemClock.elapsedRealtime()
            binding.timer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }

        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.timer.base = SystemClock.elapsedRealtime()
            binding.timer.stop()
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - initTime >= 3000) {
                    initTime = System.currentTimeMillis()
                    Toast.makeText(this@MainActivity, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
                } else {
                    finish()
                }
            }
        })
    }
}
