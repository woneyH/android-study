### 1주차 학습

- [X] 코틀린 Unit, Nothing에 대해
- [ ] 코틀린에서 클래스
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

---

### 2. 코틀린에서 객체
