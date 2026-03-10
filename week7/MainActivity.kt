package com.practice.androidbookpractice

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.practice.androidbookpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var initTime = 0L
    var pauseTime = 0L
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setUpListener()

        setupBackClick()

    }


    private fun setUpListener() {
        with(binding) {
            startButton.setOnClickListener {
                timer.base = SystemClock.elapsedRealtime() - pauseTime
                timer.start()
                changeButtonEnableStatus(startButton.isEnabled)
            }
            stopButton.setOnClickListener {
                pauseTime = SystemClock.elapsedRealtime() - timer.base
                timer.stop()
                changeButtonEnableStatus(startButton.isEnabled)
            }
            resetButton.setOnClickListener {
                timer.base = SystemClock.elapsedRealtime()
                pauseTime = 0L
                timer.stop()
                changeButtonEnableStatus(startButton.isEnabled, resetBtn = true)
            }
        }
    }

    private fun changeButtonEnableStatus(startBtn: Boolean, resetBtn: Boolean = false) {
        with(binding) {
            startButton.isEnabled = !startBtn
            stopButton.isEnabled = startBtn
            resetButton.isEnabled = startBtn || pauseTime > 0L
            if (resetBtn) resetButton.isEnabled = false
        }
    }

    private fun setupBackClick(): Unit {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - initTime >= 3000) {
                    initTime = System.currentTimeMillis()
                    toast?.cancel()
                    toast = Toast.makeText(application, "종료하려면 한 번 더 눌러주세요", Toast.LENGTH_SHORT)
                    toast?.show()
                } else {
                    toast?.cancel()
                    finish()
                }
            }
        })
    }
}
