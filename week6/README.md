# 학습 목표

- [X] 터치와 키 이벤트
- [X] 널 안전성



## 1. 터치와 키 이벤트

사용자의 터치 이벤트를 처리하고 싶다면 액티비티 클래스에 터치 이벤트의 콜백 함수인 **onTouchEvent()** 를 선안하면 됩니다.

```kotlin
 override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
```

onTouchEvent 함수를 재정의 해서 선언만 해놓으면 사용자가 이 액티비티 화면을 터치하는 순간 onTouchEvent() 함수가 자동으로 호출 됩니다.

매개변수로는 MotionEvent 객체이며, 이 객체는 터치의 종류와 터치 발생 지점 (좌푯값)이 담깁니다.

<br>

### 터치 이벤트 종류

- ACTION_DOWN : 화면을 손가락으로 누른 순간의 이벤트
- ACTION_UP : 화면에서 손가락을 떼는 순간의 이벤트
- ACTION_MOVE : 화면을 손가락으로 누른 채로 이동하는 순간의 이벤트


만약 화면에 손가락을 눌렀다가 때면 onTouchEvent() 함수는 2번 호출됩니다.
첫 번째 ACTION_DOWN 이벤트  두 번째 땔때는 ACTION_UP 이벤트가 호출됩니다.


```kotlin
override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("event", "Touch down event")
            }

            MotionEvent.ACTION_UP -> {
                Log.d("event", "Touch up event!")
            }
        }

        return super.onTouchEvent(event)
}

```

<img width="1353" height="57" alt="image" src="https://github.com/user-attachments/assets/d6e8f9b3-a633-422a-9101-558dd5b418ea" />


<br>


### 터치 이벤트 발생 좌표

MotionEvent 객체에서는 이벤트가 발생한 지점의 좌푯값을 얻을 수 있습니다.

- x : 이벤트가 발생한 뷰의 X 좌표
- y : 이벤트가 발생한 뷰의 Y 좌표
- rawX : 화면의 X 좌표
- rawY : 화면의 Y 좌표

x 와 rawX 모두 좌푯값이지만 의미하는 바는 다릅니다.
x는 터치 이벤트가 발생한 **뷰**에서의 x 값이고
rawX는 스크린, 즉 화면에서의 x 값입니다.

<br>
특정 뷰에서 setOnTouchListener 함수를 사용하면 x 값과 rawX 값의 차이를 알 수 있습니다.

```kotlin
package com.example.androidpractice

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice.databinding.TestEx6Binding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = TestEx6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnTouchListener { view, event ->
            when(event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("move", "x = ${event.x}  rawx = ${event.rawX}")
                }
            }
            true
        }
    }
}

```

결론은 x, y는 특정 뷰를 기준에서의 좌표 값  rawX, rawY는 화면 전체 기준에서 눌린 좌표값입니다.



### 키 이벤트

키 이벤트를 처리하려면 다음과 같은 콜백 함수들을 오버라이딩 해야 합니다.

- **onKeyDown** : 키를 누른 순간의 이벤트
- **onKeyUp** : 키를 떼는 순간의 이벤트
- **onKeyLongPress** : 키를 오래 누르는 순간의 이벤트

일반적인 소프트 키보드(자판)에 대한 키 이벤트 처리가 아닙니다.

해당 키 이벤트는 하드웨어 키보드 키를 입력하면 키 이벤트로 처리할 수 있습니다.

하드웨어 키보드는 뒤로 가기, 홈, 볼륨 조절 버튼 등이 존재합니다.

오버뷰 버튼과 전원 버튼을 눌러도  **onKeyDown 함수는 호출되지 않습니다.**


<br>

```kotlin
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("key-event", "키보드 눌림")
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> Log.d("key-action-event", "예전 뒤로가기 버튼 이벤트 호출")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("key-action-event", "볼륨 업 버튼 눌림")
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("key-event","키보드 땜")
        return super.onKeyUp(keyCode, event)
    }
```

<br>

**KEYCODE_BACK**  뒤로 가기 버튼 이벤트는  뒤에 onKeyDown, onKeyUp 함수를 이용하면 안 됩니다. 

최근 Android 13 부터는 **예측형 뒤로 가기** 애니메이션이 도입되었습니다.
구글은 개발자에게 onKeyDown이나 onKeyUp, onBackPressed() 같은 함수들을 사용하여 뒤로 가기 버튼 이벤트 처리를 하지 말라고 했습니다.

**최신 방식인 onBackPressedDispatcher.addCallBack()를 활용해야합니다.**

하단 코드처럼 onCreate 함수안에서 콜백함수 호출을 해야합니다.

```kotlin
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_ex6)
        onBackPressedDispatcher.addCallback(this,c)
    }

    private val c = Child()
}

class Child: OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
        Log.d("뒤로가기 버튼 ","버튼이 눌림")
    }
}
```
<img width="1747" height="211" alt="image" src="https://github.com/user-attachments/assets/82df8071-67e0-436d-bc3f-25fb39275949" />


---

<br>

## 2. 널 안전성

코틀린은 NPE에 대한 유연하고 방어적인 프로그래밍이 가능합니다.

널 안전성을 활용해 널 포인트 예외가 발생하지 않도록 코드를 작성할 수 있습니다.

코틀린은 기본적으로 모든 변수에 null을 허용하지 않습니다. 만약 변수에 null을 담고 싶다면 타입 뒤에 물음표를 붙여 명시적으로 Nullable 타입으로 선언하면 됩니다.

### 2-1 Nullable 타입과 Non-Nullable 타입

**Non-Nullable type (default)**: null을 넣으려 하면 컴파일 에러 발생합니다.
```kotlin
 var a: String = "TEST"
 // a = null  //컴파일 에러 발생!
```

**Nullable 타입** : 타입 뒤에 **?** 를 붙여 null을 허용합니다.
```kotlin
var a: String? = "TEST"
a = null //정상 동작 에러X
```


### 2-2 Safe Call Operator  (?. 연산자)

Nullable타입이라면 해당 변수에 null일 가능성이 존재하는 변수인 것 입니다.
따라서 언제든 NPE(Null Point Exception)이 발생할 수 있습니다.
이때 NPE방지를 위해 안전한 호출 연산자가 존재합니다.

**?.** 연산자를 사용하면 안전하게 객체의 프로퍼티나 메서드에 접근할 수 있습니다.

```kotlin
val name: String? = "kim"
println(name.length)  //에러 발생
```

위 코드처럼 널 허용으로 선언한 변수의 멤버에 접근할 때는 반드시 **?. 연산자를 사용해야 합니다.**

```kotlin
val name: String? = "kim"
println(name?.length)
```

Safe Call을 더 자세히 말하면 객체가 null이면 null을 반환하고 null이 아니면 멤버에 대한 접근을 합니다.  NPE를 발생시키지 않습니다.

### 2-3 Elvis Operator (?: 연산자)

변수가 null 일 때 null 대신 사용할 기본값을 지정하고 싶을 때 사용합니다.

**?:** 기준 왼쪽의 결과가 null이 아니면 그 값을 그대로 사용하고, null이면 오른쪽 값을 반환합니다.

```kotlin
val test: String? = null
println(test?.length ?: 0) //null 이므로 0을 출력하게 됩니다. 
```


### 2-4 Not-null Assertion Null 아님 단언 연산자  (!!)

어떤 변수가 **절대 null이 아님**을 개발자가 컴파일러에게 강제로 보장하는 연산자입니다.

- 이 연산자를 쓰면 Nullable 타입을 Non-Nullable 타입으로 강제 변환합니다.
- 만약 실제 값이 null이면 NPE가 발생하므로 가급적 사용을 피하는게 좋습니다.

```kotlin
val name: String? = "Kotlin"
val length: Int = name!!.length //name이 절대 null아 아니라고 단언함

val name2: String? = null
val length2: Int = name!!.length //NullPointException 발생
```
