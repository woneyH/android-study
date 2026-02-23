# 학습 목표

- [ ] 널 안전성
- [ ] 터치와 키 이벤트



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
