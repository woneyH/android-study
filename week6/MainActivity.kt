package com.example.androidpractice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.androidpractice.databinding.CalculatorBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculatorBinding = CalculatorBinding.inflate(layoutInflater)
        setContentView(calculatorBinding.root)
        val resultText = calculatorBinding.result
        calculatorBinding.root.children
            .filterIsInstance<Button>()
            .forEach { btn ->
                btn.setOnClickListener {
                    resultText.text = btn.text
                }
            }

    }
}
