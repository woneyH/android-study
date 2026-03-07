# 학습 목표

- [X] SAM 기법
- [ ] 리소스에 대해


## 1. SAM (Single Abstract Method)

**SAM** 은 추상 메서드가 딱 하나만 있는 인터페이스를 말합니다.
Java에서는 함수형 인터페이스라고 부릅니다.

```kotlin
interface OnClickListener {
  fun onClick(view: View)
}
```

위 코드처럼 추상 메서드 단 한개만 존재합니다.

SAM 인터페이스를 구현할 때, 람다식으로 대체할 수 있는 기능입니다. 
자세한 설명을 위해 하단 예시코드로 적용해보겠습니다.


```java
public class ListenerTest {
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void performClick(String text) {
        if(clickListener!=null) {
            clickListener.click(text);
        }
    }

}
```

<br>

```java
interface ClickListener {
    void click(String text);
}
```


이제 ListenerTest에 존재하는 setClickListener 메서드를 사용할 때 인터페이스를 구현해서 파라미터로 넘겨야 합니다. 

과거에는 아래처럼 object 키워드를 사용해 인터페이스의 메서드를 직접 오버라이드했습니다.

```kotlin
val listenerTest = ListenerTest()

listenerTest.setClickListener(object: ClickListener {
  override fun click(text: String) {
    println("클릭 이벤트 발생: $text")
  }
})


```

ClickListener는 추상 메서드가 click 단 하나라 SAM 인터페이스입니다. 따라서 Kotlin 컴파일러는 우리가 어떤 메서드를 오버라이드하려는지 알고 있습니다.

덕분에 익명 객체를 생성하는 대신, 아래처럼 간결한 람다식으로 대체할 수 있습니다.

```kotlin
val listenerTest = ListenerTest()

listenerTest.setClickListener { text ->
  println("클릭 이벤트 발생: $text")
}
```

object와 override 키워드를 제거하여 더욱 간결한 코드가 완성되었습니다.


파라미터로 람다식을 넘기기 위해 만족해야 하는 **필수 조건 3가지**를 정리하겠습니다.

- **1. 추상 메서드가 오직 하나일 것**: 인터페이스 내부에 구현해야 할 추상 메서드는 반드시 하나만 존재해야 합니다.  이미 본문이 구현되어 있는 default 메서드는 몇 개가 있든 상관 없습니다.
- **2. 추상 클래스가 아닌 인터페이스일 것**: 반드시 인터페이스로 선언되어야 합니다. 만약 추상 클래스이고 안에 추상 메서드 딱 한개라도 람다식으로 변환 불가합니다. 기존 방식대로 익명 객체 사용해야 합니다.
- **3.언어에 따른 선언 방식**: 만약 Kotlin으로 작성된 인터페이스라면 인터페이스 선언부 앞에 fun 키워드를 반드시 붙여야 합니다.


#### 3번 조건에 대한 코드 

```kotlin
fun interface Clickable {
    fun onClick(text: String)
}

class Click {
    var clickListener: Clickable? = null

    fun click(text: String) {
        clickListener?.onClick(text)
    }
}

val c = Click()

c.clickListener = Clickable {println("클릭 성공! $it")}

c.click("Test1")
```

위 코드처럼 SAM 기법을 활용하려면 
kotlin에서는 interface앞에 fun 키워드를 작성해야 합니다.

그리고 Kotlin은 setter를 컴파일러에서 자동으로 만들어주니 setter 메서드를 선언할 필요 없습니다.

대신 clickListener는 **SAM 생성자**를 활용합니다.   

c.clickListener = Clickable {println("클릭 성공! $it")}
