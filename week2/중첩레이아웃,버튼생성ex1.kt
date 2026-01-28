package com.example.androidpractice

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

/**
 *  UI 프로그래밍 방식으로
 *  번복문과 객체 테스트 코드
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnFactory: ButtonCreate = ButtonCreate(this)

        val layout1 = LinearLayout(this).apply {
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
        }

        val layout2 = LinearLayout(this).apply {
            gravity = Gravity.CENTER
            orientation = LinearLayout.HORIZONTAL
        }

        for (i in 1..4) {
            if (i >= 3) {
                layout2.addView(btnFactory.createBtn("버튼$i"))
            } else {
                layout1.addView(btnFactory.createBtn("버튼$i"))
            }
        }
        layout1.addView(layout2)

        setContentView(layout1)
    }
}


class ButtonCreate(private val context: Context) {
    fun createBtn(btnName: String): Button {
        return Button(context).apply {
            text = btnName
        }
    }
}
