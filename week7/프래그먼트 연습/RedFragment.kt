package com.practice.androidbookpractice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.practice.androidbookpractice.databinding.FragmentRedBinding

/**
 * 상속받은 Fragment에서 보조생성자 Fragment(@LayoutRes int contentLayoutId)
 * R.layout.x  --> R클래스 layout 상수값을 넣으면 보조생성자의 본문에서
 * mContentLayoutId = contentLayoutId;  필드변수를 초기화합니다.
 * onCreateView가 저절로 호출되며 초기화했던 필드변수를 토대로 스스로 inflate를 처리해 View를 반환합니다.
 */
class RedFragment: Fragment(R.layout.fragment_red) {
    var call: CallSign?= null

    interface CallSign {
        fun call()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentRedBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)
        binding.myCommonButton.btn1.setOnClickListener {
            call?.call()
        }
    }
}
