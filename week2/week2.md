## 2주차 학습
---
- [X] 뷰 클래스
- [ ] apply, also 함수
- [X] XML 방식 뷰 화면 코딩 학습

---

## 학습 목표

- 뷰 클래스에 대해 자세히 공부하기
- apply, also 등 객체 구성에 대한 코틀린 함수 공부하기
-  View XML 다양한 속성 학습하기



---

## View class

[작성한 글]
https://0dedicated.tistory.com/63

핵심은 View 객체는 위젯이라고 불리며 ViewGroup은 레이아웃이라고 불립니다.


---

## apply, also 함수

#### apply : apply 블럭 내부에서 수신 객체를 this로 참조하여 객체의 이름을 일일이 쓰지 않고 속성을 바로 변경할 수 있는 함수입니다.

apply는 보통 객체 생성 직후 초기화할 때 가장 많이 사용됩니다.

#### 예시코드 

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
