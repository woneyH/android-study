## 3주차 학습
---


## 학습 목표
- [X] 엑티비티에 대해 자세히 공부하기
- [X] 액티비티 생명주기
- [ ] 기초적인 다양한 XML 태그 여러 개 학


---

## 1. 액티비티

Activity는 사용자가 눈으로 보고 터지하는 화면 하나입니다.

앱을 실행하고 메시지 채팅 화면을 누르면 채팅 화면 액티비티, 친구 화면을 누르면 친구 목록 액티비티 이런식으로 액티비티가 넘어갑니다.

##### 액티비티의 핵심 역할
1. 사용자 인터페이스(XML)를 화면에 그린다 (setContentView)
2. 사용자의 터지나 클릭 이벤트를 받아서 처리한다.

---


## 2. Activity 생명주기

액티비티는 단순히 "켜짐/꺼짐" 만 있는 게 아니라,  
**탄생->보임->활성화->가려짐->죽음** 단계를 거칩니다.

각 단계에서 호출되는 **콜백 메서드**를 오버라이드하여 리소스를 관리합니다.


| 콜백 메서드 |   상태   | 설명 개발자가 할 일 |
|------------| ----------|----------------------|
| **onCreate()** | 생성      | 액티비티가 최초 생성될 때 딱 한 번 실행<br> **setContentView()**로 렝아웃 설정, |
| **onStart()** |  시작  |  액티비티가 사용자에게 보이기 직전. <br> UI를 관리하는 코드를 초기화  <br> 매우 짧게 지나가며 곧바로 onResume()으로 이어집니다. |
| **onResume()** | 재개 |  액티비티가 포그라운드에 있고 사용자와 상호작용이 가능해집니다.<br> 애니메이션 시작, 카메라 미리보기 실행, 센서 리스너 등록  <br> 앱이 실제로 실행 중인 핵심 상태입니다. |
| **onPause()** | 일시정지 | 액티비티가 보이지만 포커스를 잃은 상태(반투명 다이얼 로그 노출 등) <br> 배터리에 영향을 주는 작업들을 중단합니다.  <br> 매우 짧은 시간이므로 무거운 저장 작업은 피해야 합니다.
| **onStop()** | 정지 | 액티비티가 더 이상 사용자에게 보이지 않는 상태입니다. <br> 데이터를 데이터베이스에 저장하거나 무거운 리소스 해제 |
| **onRestart()** | 재시작 | onStop() 상태였던 액티비티가 다시 시작될 때 호출합니다.  |
| **onDestroy()** | 소멸 | 액티비티가 완전히 종료되거나 시스템이 의해 제거됩니다. <br> onCreate()에서 생성한 모든 리소스를 해제합니다. |


### onPause() 와 onStop() 의 차이

### onPuase()란?
onPause()와 onStop() 모두 액티비티가 사용자에게 멀어질 때 호출되지만 기능적 차이는 많이 존재합니다.

onPause()는 액티비티가 여전히 보이지만 직접 만질 수 없는 상태입니다.
대표적인 상황은 내 앱 위에 반투명한 설정창이나 권한 요청 다이얼로그가 떴을 때입니다.

**onPause()주의점 :** 다음 액티비티가 나타나기 전까지 시스템이 대기하는 상태이므로, 무거운 작업을 하면 화면 전환이 버벅거릴 수 있습니다. **가벼운 작업만 처리**해야합니다.


### onStop()이란?

액티비티가 완전히 백그라운드로 넘어가서 화면에서 사라진 상태입니다.

예를들어 사용자가 홈 버튼을 눌러 바탕화면으로 나갔거나, 완전히 새로운 화면으로 이동했을 때 입니다.

**onStop() 주의점** : 사용자가 앱을 다시 볼지 안 볼지 모르는 상태이므로, CPU를 많이 소모하는 작업이나 무거운 데이터 저장은 보통 이곳에서 마무리하는 것이 안전합니다.


---

## 3. 액티비티 생명주기 예시 코드

Logcat으로 메서드 호출 로그를 확인하는 코드입니다.

```kotlin
package com.example.androidpractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private val logTag = "LifeCycleLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(logTag, "onCreate : 액티비티 생성되었습니다. (최초 1회)")
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart : 화면이 보이기 시작합니다.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag,"onResume : 사용자와 상호작용이 가능합니다. (실행 중")
    }

    override fun onPause() {
        super.onPause()
        Log.d(logTag, "onPause : 포커스를 잃었습니다. (일시정지)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(logTag, "onStop : 화면에서 완전히 사라짐")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(logTag, "onRestart : 정지되었던 액티비티가 다시 시작됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "onDestroy : 액티비티가 메모리에서 제거됨")
    }
}
```


**앱이 실행될 때 라이프사이클**

onCreate -> onStart -> onResume

<img width="1667" height="97" alt="image" src="https://github.com/user-attachments/assets/3dc1a63c-16de-44df-9f95-877cd4f78ed4" />


**홈 화면 눌러 나갈 때**

onPause -> onStop

<img width="1677" height="81" alt="image" src="https://github.com/user-attachments/assets/96cf9d77-cec7-4642-a371-dc86def09293" />


**홈 화면으로 나갔던 앱 다시 돌아올 때**

onRestart -> onStart -> onResume

<img width="1747" height="88" alt="image" src="https://github.com/user-attachments/assets/cb6d3858-5b92-45f6-a93a-3f7c583714fc" />



**앱에서 완전히 종료될 때 뒤로가기나 앱 종료**

onPause -> onStop -> onDestroy

<img width="1563" height="83" alt="image" src="https://github.com/user-attachments/assets/3c21b11a-7989-4473-a812-78f0d1cbdf92" />


**화면 회전할 때**
onPause -> onStop -> onDestroy -> onCreate  기존 액티비티가 죽고 다시 태어납니다.

<img width="1703" height="187" alt="image" src="https://github.com/user-attachments/assets/e32d87a8-286d-488f-9970-8f7d5a67ab1b" />


---

## 📝 액티비티 정리

### 1. 액티비티의 본질

단순히 화면 하나가 아니라, **사용자와 앱이 만나는 창구입니다.**
개발자는 각 상태에 맞춰 리소스를 넣고 빼는 작업을 잘해줘야 합니다.

### 2. 생명주기의 핵심

- onCreate() <-> onDestroy() : 전체적인 생성과 소멸은 한 번씩 호출됩니다.
- onStart() <-> onStop()  : 화면이 보이기 시작함과 완전히 사라짐입니다.
- onResume <-> onPause() : 사용자가 조작 가능한 상태와 포커스를 잃은 상태입니다.

### 3. 결론
개발자가 결국 생명주기를 잘 다루면 다음 3가지를 보장합니다.
- **안정성** : 앱이 갑자기 꺼지지 않음
- **성능** : 사용하지 않는 리소슬르 제때 반납하여 폰이 느려지지 않습니다.
- **연속성** : 화면을 돌리거나 다른 앱을 다녀와도 사용하던 데이터가 유지됩니다.
