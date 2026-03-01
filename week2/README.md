## 2주 차 학습
---
- [X] 뷰 클래스
- [X] apply, also 함수
- [X] XML 방식 뷰 화면 코딩 학습

---

## 학습 목표

- 뷰 클래스에 대해 자세히 공부하기
- apply, also 등 객체 구성에 대한 코틀린 함수 공부하기
-  View XML 다양한 속성 학습하기



---

## 1. View class

[작성한 글]
https://0dedicated.tistory.com/63

핵심은 View 객체는 위젯이라고 불리며 ViewGroup은 레이아웃이라고 불립니다.


---

## 2. apply, also 함수

#### apply : apply 블록 내부에서 수신 객체를 this로 참조하여 객체의 이름을 일일이 쓰지 않고 속성을 바로 변경할 수 있는 함수입니다.

apply는 보통 객체 생성 직후 초기화할 때 가장 많이 사용됩니다.

#### also : 객체를 사용하는 흐름을 끊지 않고, 그 객체를 가지고 중간에 다른 작업을 수행하고 싶을 때 사용하는 함수입니다.

also는 객체의 속성을 수정하는 것보다 객체를 사용하는 것에 초점이 맞춰져 있습니다.

- ✅ 디버깅할 때
- ✅ 유효성 검사
- ✅ 외부 알림

also 블록은 실행될 때 규칙이 존재합니다.
1. 참조(it) : 블록 안에서 객체는 it이라는 이름으로 불립니다.
2. 반환(self) : 블록 안에서 무슨 짓을 하든, also의 결괏값은 처음 들어온 객체 그 자신입니다.

#### apply 예시 코드 

```kotlin
        val text0 = TextView(this)
        text0.text = "테스트0"

        //apply 사용 예시
        val text1 = TextView(this).apply {
            text = "테스트1"
        }


        val layout1 = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(text0)
            addView(text1)
        }

        setContentView(layout1)

```


#### also 예시 코드

```kotlin
fun fn() {
    val numbers = mutableListOf("one","two","three")

    val result = numbers
        .first()
        .also { println("가져온 값은 $it") }
        .uppercase()

    println("최종 결과 $result")
}

fn()

// 출력 결과
//가져온 값은 one
//최종 결과 ONE
```

참고로 also 블록 안에 존재하는 파라미터 it 대신 이름을 다른 것으로 바꿀 수 있습니다.

아래는 it를 currentUser라는 이름으로 바꾼 코드입니다.


```kotlin
fun fn(user : User) {
    user.also { currentUser ->
        require(currentUser.age > 19)
        println("성인 인증 완료")
    }

}


class User(val age : Int)

fn(User(20))
//출력결과 : 성인 인증 완료
```


### 2-2 apply와 also 결론

apply : 객체 내부 속성을 초기화하거나 설정할 때
also : 객체를 다른 함수에 넘기거나 확인(로그) 할 때   부수적 작업
