# 학습 목표

- [X] 뷰를 배치하는 레이아웃
- [X] 람다 표현식


---

## 1. 레이아웃 배치 방법들

<br>
### 1-1 LineaerLayout  선형으로 배치

LineaerLayout은 Android에서 자식 뷰들을 수직 또는 수평으로 일렬로 배치하는 레이아웃입니다.

#### 기본 방향 설정
**android:orientation** 가장 중요한 속성입니다.
- vertical : 자식 뷰들을 세로로 배치
- horizontal : 자식 뷰들을 가로로 배치

#### 가중치 관련 속성
**android:layout_weight** 자식 뷰에서 설정합니다.

- 남은 공간을 비율로 분배합니다.
- 예를 들어 weight="1" 과 weight="2"를 설정하면 1:2 비율로 공간 차지합니다.
- layout_width 혹은 layout_height를 "0dp"로 설정해야 올바르게 작동됩니다.

#### android:gravity, android:layout_gravity 

gravity는 다음과 같습니다.

- 내부 콘텐츠의 정렬 위치 텍스트나 내부 콘텐츠의 정렬 위치입니다.
- 값은 : top, bottom, left, right, center, center_vertical, center_horizontal
- 복합 사용도 가능합니다 : center_vertical|right

<br>

### 1-2 RelativeLayout  상대 위치로 배치

RelativeLayout은 상대 뷰의 위치를 기준으로 정렬하는 레이아웃 클래스입니다.

즉, 화면에 이미 출력된 특정 뷰를 기준으로 방향을 지정하여 배치하는 레이아웃입니다.

특정 뷰를 지정하는 방법은 id속성을 이용합니다.

기준 뷰의 기준으로 방향을 지정하는 속성입니다.
(to와 above, below는 새로운 뷰가 어느방향으로 갈지에 대한 이동에 대한 속성입니다.)

- android:layout_above : 기준 뷰의 위쪽에 배치
- android:layout_below : 기준 뷰의 아래쪽에 배치
- android:layout_toLeftOf : 기준 뷰의 왼쪽에 배치
- android:layout_toRightOf : 기준 뷰의 오른쪽에 배치

기준 뷰의 기준으로 정렬하는 **align** 속성

- android:layout_alignTop : 기준 뷰와 위쪽을 맞춤
- android:layout_alignBottom : 기준 뷰와 아래를 맞춤
- android:layout_alignLeft : 기준 뷰와 왼쪽을 맞춤
- android:layout_alignRight : 기준 뷰와 오른쪽을 맞춤
- android:layout_alignBaseline : 기준 뷰와 텍스트 기준선을 맞춤

to = 이동  align = 정렬

<br>

### 1-3 FrameLayout  겹쳐서 배치

FrameLayout은 뷰를 겹쳐서 출력하는 레이아웃입니다. 카드를 쌓듯이 뷰를 추가한 순서대로 위에 겹쳐서 계속 출력하는 레이아웃입니다.

<br>

### 1-4 GridLayout  표 형태 배치




---

## 2. 코틀린 람다식

람다 함수는 익명 함수를 정의하는기법입니다. 람다 함수는 주로 함수를 간단하게 정의할 때 이용합니다.


**일반 함수 선언 형식**
```kotlin
fun 함수명(매개변수) {함수 본문}
```

**람다 함수 선언 형식**
```kotlin
{ 매개변수 -> 함수 본문}
```

람다 함수는 fun 키워드를 이용하지 않고 함수 이름도 존재하지 않습니다.
람다 함수는 중괄호로 표현합니다.


람다 함수의 규칙

- 람다 함수는 {}로 표현
- {} 안에 화살표가 있으며 화살표 왼쪽은 매개변수, 오른쪽은 함수 본문입니다.
- 함수의 반환값은 함수 본문의 마지막 표현식입니다.


람다 함수의 사용 예시

```kotlin
val sum = {no1: Int, no2: Int -> no1+no2}
println(sum(5,7))  //12

//선언과 동시에 호출
{no1: Int, no2: Int -> no1+no2}(5,7)    //12
```


람다 함수는 이름이 없는 함수이므로 함수명으로 호출할 수 없습니다. 
위 코드처럼 람다 함수를 변수에 대입하거나, 선언과 동시에 호출해야 합니다.


매개변수가 0개 ,  1개 일 때의 람다 함수

**매개변수 0개**
```kotlin
{->println(50)}

혹은

{println(50)}
```


**매개변수 1개**
```kotlin
val some = {no: Int -> println(no)}
//혹은
val some: (Int) -> Int = {it}
```

람다 함수 매개변수가 1개일 때 기본 구조는 다음과 같습니다.

```kotlin
val 변수명: (매개변수타입) -> 반환타입 = {매개변수(생략 가능) -> 실행 코드}
```

#### 변수에 함수를 담을 때 함수 타입 선언

함수 타입을 이용해 변수에 함수를 대입하는 예시입니다.

```kotlin
val fn: (Int,Int) -> Boolean = {no1: Int, no2: Int ->
  no1==no2
}
//위 코드에서 fn 타입을 명시적으로 (Int,Int)->Boolean 으로 작성했습니다.
//매개변수 2개 다 Int라는 것을 컴파일러는 알 수 있으므로 매개변수 타입을 생략할 수 도  있습니다.

val fn: (Int,Int) -> Boolean = {no1,no2 ->
  no1==no2
}
```


함수 타입 선언의 핵심은 다음과 같습니다.

- **val 변수명: (들어가는 매개변수 타입) -> return 타입 = {람다 함수 수행문}**


