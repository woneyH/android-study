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

<br>

## 2. 리소스 활용

리소스 디렉토리와 파일은 이름을 지을 때 규칙을 반드시 지켜야 새로 생성하고 이용할 수 있습니다.

res디렉토리 아래에 개발자가 임의의 이름을 붙인 디렉토리를 만들 수 없습니다.

즉, res 디렉토리 하위 디렉토리는 반드시 규칙에 존재하는 이름만 사용할 수 있습니다.

**values 디렉토리에 추가하는 파일을 제외하고는 모두 이름 작성 규칙을 지켜야합니다.**


#### 안드로이드 앱의 리소스 종류

| **디렉토리명** |  **리소스 종류** |
| --------------- | ------------------- |
| **animator** |  속성 애니메이션 XML |
| **anim** | 트윈 애니메이션 XML |
| **color** | 뷰 상태에 따른 색상 상태 목록 정의 XML |
|  **drawable** |  이미지 리소스 |
| **mipmap** | 앱 실행 아이콘 리소스 |
| **layout** | 레이아웃 XML |
| **values** | 단순 값으로 이용되는 리소스 |
| **font** | 글꼴 리소스 |


### 2-1 values 디렉토리 

리소스도 다양한 내용들이 존재하지만 우선 values만 정리하고 나머지는 필요할 때 마다 찾으면 될 것 같다.

values와 달리 다른 모든 리소스들은 

ex) drwable 같은 리소스는 파일 1개(A.xml) = 리소스 1개 로 파일명이 곧 ID 입니다. (R 클래스에 들어가는 리소스 입니다.)

하지만 values안에 존재하는 string.xml, color.xml, dimen.xml, style.xml 같은 경우에는 

리소스 파일은 파일명이 R인 파일에 식별자로 등록되지 않고  리소스 파일에 값을 지정한 태그의 name 속성값이 등록됩니다.

즉, values는 파일1개 = 여러 개 리소스 존재


<br>

XML에서는 리소스를 접근할 때 R을 안 사용합니다.
```xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:text="@string/test1"/>
    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/test"/>
```

위 xml 파일처럼 @를 이용해 리소스에 접근합니다.

XML은 AAPT 번역기를 사용합니다. 여기서 **@** 의 의미는 : 리소스 파일에 무언가를 쓴다는 의미입니다.
<br>

kotlin에서는 R을 사용해 R.java에 리소스 고유 숫자를 접근하고 호출합니다.

```kotlin
        binding.text1.text = resources.getString(R.string.test2)
        //binding.text1.text = getString(R.string.test2)  resources 생략 가능
        //binding.text1.setText(R.string.test2) setter 사용 가능

        binding.img1.setImageResource(R.drawable.test)

```




