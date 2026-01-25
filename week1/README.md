### 1주차 학습

- [X] 코틀린 Unit, Nothing에 대해
- [X] 코틀린에서 클래스
- [ ] 코틀린 상속

--- 

### 학습 목표

- 코틀린에서 객체지향적 사고로 클래스 익숙해지기
- 코틀린 스타일 클래스와 생성자, 상속 학습하기
- Unit과 Nothing 객체애 대해 알아보기


--- 

### 1. Unit과 Nothing

#### Unit

Unit : 자바에서 void 같은 느낌임 코틀린은 모든것이 객체라고 볼 수 있는데 반환값이 없을 때도 Unit이라는 객체를 반환한다.

Unit은 변수 타입으로 선언할 수 있지만 의미는 없다. 

Unit은 반환문이 없음을 명식적으로 나타낼 때 Unit 타입을 사용한다. 

Unit은 아래 코드처럼 메서드가 단 한개 존재한다. 부모 Any의 메서드를 오버라이딩 한 것 뿐


``` kotlin
/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin

/**
 * The type with only one value: the `Unit` object. This type corresponds to the `void` type in Java.
 */
public actual object Unit {
    override fun toString(): String = "kotlin.Unit"
}

```

### Nothing

Nothing : 함수가 종료되지 않음을 의미한다. (무한 루프, 예외 발생)

Any가 모든 객체의 최상위라면 Nothing은 모든 객체의 최하위 객체이다.

```kotlin
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message) // 함수가 정상 종료되지 않고 예외를 던짐
}

val s: String = name ?: fail("Name required") // fail은 Nothing을 반환하므로 String 자리에 올 수 있음

//ex 무한루프 컴파일
fun loop(): Nothing {
    while(true) {
        //무한반복
    }
}

fun main() {
    loop()
    // [컴파일 단계]
    // 컴파일러: "loop()는 Nothing을 반환하네? 그럼 아래 println은 절대 실행 안 되겠군." (OK)
    // 컴파일은 성공하지만, IDE(인텔리제이 등)에서 이 줄은 흐리게 표시되며 "Unreachable code"라고 알려줍니다.
    println("컴파일은 되지만 여기에 영원히 도달하지 못함") 
}
```

Nothing은 의도적인 예외 발생하거나 아직 구현되지 않은 기능 TODO()

🔥 핵심: 자바라면 컴파일조차 못하는 코드를 Nothing 객체로 컴파일을 가능하게 합니다. 

<br>

---

### 2. 코틀린에서 객체

 자바에서 클래스를 사용하는 것과 비슷하다.
 kotline 특유의 문법적 특성만 자세히 정리해보았다.

 #### 2-1 주 생성자와 보조 생성자

 코틀린은 주 생성자와 보조 생성자로 구분한다.
 한 클래스에 주 생성자만 선언할 수도 있고 보조 생성자만 선언할 수 있다.
 주 생성자를 생략해도 코틀린은 매개변수가 없는 주 생성자를 자동으로 만들어줍니다.

 코틀린에서 코드를 더 읽기 좋게 만들기 위해 클래스 내부 작성 순서는 다음과 같습니다.
 

 ```kotlin
 class Main(name : String) {   // [1. 주 생성자 + 지역 변수 혹은 멤버 변수]
    //[2. 추가 프로퍼티]
    private var name : String  = ""  
    private var age : Int = 0

    //[3. init 불록] : 주 생성자 직후 실행
    init {
        this.name = name
        println("name : $name")
    }

    //[4. 보조 생성자] : 주 생성자와 매개변수가 달라야 함
    constructor(name : String, age : Int) : this(name) {
        this.age = age
        println("보조생성자")
    }

    //[5. 메서드]
    fun printName() {    
        println("name $name")
    }

    // [6. 컴패니언 객체] : 가장 마지막
    companion object {
        fun create(name: String): Main = Main(name)
    }

```

정리하자면 {변수 -> init -> 보조 생성자 -> 함수 -> compantion} 순이 정석입니다.


<br>
주 생성자를 통해 멤버 변수를 선언하는 방법은 다음과 같다.

```kotlin
    //해당 코드의 name과 age는 지역 변수입니다.
    //이 값들은 클래스 외부에서 test1.name으로 접근 할 수 없습니다.
    class Test1(name : String, age : Int)

    //var나 val 키워드를 추가하면 매개변수로 선언할 수 있습니다.
    //참고로 원래 함수 매개변수를 선언할 때 var나 val 키워드 사용 불가합니다.
    class Test2(private val name : String , val age : Int)
```


<br>

 #### 2-2 정적 멤버가없다 (static -> companion object)

 자바의 static 키워드가 코틀린에는 없습니다. 대신 companion object를 사용합니다.
 클래스 내부 최하단에 작성합니다.

 ``` kotlin
 static int MAX = 10;  //자바 코드
companion object { const val MAX = 10} // 코틀린 선언
```


#### 2-3 보조 생성자

주 생성자가 존재하는 클래스라면 보조 생성자에 this()를 이용해 주 생성자를 호출해야 합니다.

보조 생성자를 통해 객체를 생성하더라도 init{} 블록은 무조건 실행합니다.

<br>
<strong>🔄 호출 메커니즘</strong>

1. 보조 생성자 호출
2. 주 생성자 위임 : this()에 의해 주 생성자로 제어권 넘어간다. (주 생성자가 없어서 this()를 생략해도 매개변수 없는 주 생성자를 호출함 this())
3. 프로퍼티 및 init 블록 실행
4. 보조 생성자 본문 실행

