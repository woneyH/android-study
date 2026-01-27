## setContentView 메서드란?

**Activity**와 **Layout**을 연결해주는 가장 핵심적인 메서드입니다.
액티비티가 시작될 때 사용자에게 보여줄 화면 구성을 이것으로 하라라고 지정하는 역할을 합니다.



**🚨 setContentView 메서드는 반환값 void입니다. 반환하지 않으니 변수로 따로 받을 필요성 없습니다.**

### setContentView Arguments

setContentView 메서드는 3가지 방식으로 오버로딩되어 있습니다.

#### 1. 리소스 ID 사용하는 경우

인수로 **R.layout.activity_main** 과 같은 XML 파일 리소스 ID를 인수로 넣는 경우입니다.

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //인수로 xml 리소스 ID 사용
    }

    //호출한 setContentView 메서드
   @Override
    public void setContentView(@LayoutRes int layoutResID) {
        initializeViewTreeOwners();
        getDelegate().setContentView(layoutResID);
    }
```

<br>

#### 2. View 객체를 직접 넣는 경우


인수로 View 객체를 직접 넣는 경우입니다.
XML 없이 코드로만 UI 짤 때 사용합니다. 기본적인 너비와 높이는 **MATCH_PARENT**로 설정됩니다.

참고로 View 객체의 모든 하위 객체를 넣어도 상관없습니다. (다형성)
예를 들어 인수로 LinearLayout이나 TextView만 넣어도 상관없습니다.

```kotlin
        val test1 = TextView(this).apply {
            text = "Hello?"
            typeface = Typeface.DEFAULT_BOLD
        }

        setContentView(test1)
```

<br>

#### 3. View 객체와 레이아웃 파라미터를 같이 넣는 경우

   인수로 **View 객체**와 해당 뷰의 크기나 속성을 정의한 **LayoutParams**를 넣습니다.
   뷰를 추가하거나 특정 크기나 제약 조건을 함께 명시하고 싶을 때 사용합니다.



```java
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        initializeViewTreeOwners();
        getDelegate().setContentView(view, params);
    }
```

**사용 예시 코드**

```kotlin
        val test1 = TextView(this).apply {
            text = "Hello?"
            typeface = Typeface.DEFAULT_BOLD
        }

        val params1 = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(test1,params1)
```



### 내부 동작 로직

setContentView가 호출되면 단계별 처리 과정은 다음과 같다.

1. Window 객체 호출
2. DecorView 초기화
3. Content Parent 찾기
4. View 추가
5. 화면 갱신

   Activity -> PhoneWindow -> DecorView -> mContentParent 
