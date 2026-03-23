# 학습 목표

- [X] API 레벨 호환성
- [ ] 퍼미션 설정


---

## 1. API 레벨 호환성 고려

API 레벨은 앱 개발에 큰 영향을 주는 중요한 정보입니다. 

```gradle
        minSdk = 26
        targetSdk = 36
```

만약 build.gradle에 API 레벨 설정을 상단과 같이 했다면 

targetSdk 36은 36버전의 API로 앱을 개발한다는 의미입니다.  

minSdk 26은 **이 앱은 26버전 기기부터 설치가 가능합니다.**

하지만 API 26버전의 기기는 API 26 이후로 나온 기능들을 사용할 수 없습니다.

즉, 코드에서 API 31 버전에 추가된 클래스를 사용했다면  31버전 하위 기기들에서는 오류가 발생합니다.



<img width="908" height="273" alt="image" src="https://github.com/user-attachments/assets/1f8dc082-cb2a-4695-950d-f2930f1ba379" />


상단 이미지처럼 android studio complie Sdk가 아무리 높아도 minSdk보다 더 높은 버전의 API에서 나온 기능들을 사용하면 에러가 발생합니다.


에러를 해결하는 방법은 애너테이션을 사용하면 됩니다.


**1. @RequiresApi(Build.VERSION_CODS.버전코드)**

```kotlin
@RequiresApi(Build.VERSION_CODES.S)
```

**@RequiresApi** 어노테이션은 함수 내부적으로 minSdk보다 상위의 기능을 사용해도 어노테이션 인수로 들어온 버전 이상만 제공한다고 개발자가 명시적으로 선언한 것입니다.

따라서 린트(Lint) 해당 함수 내부에서 빨간 줄(에러)를 발생시키지 않습니다.

<br>
하지만 안드로이드 스튜디오에서 오류를 무시하는 설정일 뿐입니다.

실제 앱이 실행되고 특정 API 버전의 기능보다 낮은 API 레벨의 기기에서는 호환성 문제가 발생합니다.

따라서 API 레벨 호환성 문제를 막으려면 직접 코드로 처리해줘야 합니다.


```kotlin

if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            notification()
        }

```

이렇게 조건문이나 다양한 방법으로 현재 기기의 SDK API 버전과 특정 기능이 제공된 API 버전을 비교하여

API 호환이 된다면 함수를 호출하는 형식으로 API 호환성 문제를 해결하면 됩니다.

> `참고로 API 레벨에서 Build.VERSION_CODES.S 의 뜻은 Snow Cone을 의미입니다. 즉 안드로이드 버전 별 알파벳 코드명을 정수 상수로 구현해놓은 필드들입니다. `

<br>

## API에 대한 요약

- **minSdk** : 특정 API 버전 미만 폰은 앱 설치 금지
- **complieSdk** : 개발자가 개발할 때 안드로이드 complieSdk 버전의 최신 문법과 기능을 다 가져다 씀
- **targetSdk** : 앱을 실행해 줄 특정 기준의 API 레벨입니다. 예를 들어 targetSdk 보다 상위 API 기기에서 실행한다면 targetSdk 규칙으로 적용(다운그레이드?)하여 앱을 실행합니다.

<br>
보통 구글의 강력한 API 제재로 targetSdk는 가장 최신 API를 사용합니다.




<br>

---

## 2. 퍼미션

퍼미션은 앱의 특정 기능에 부여하는 접근 권한입니다.

안드로이드에서 제공하는 기능 외 내가 만든 기능을 다른 앱에서 사용할 수 없도록 보호하고 권한을 얻은 앱에서만 허용하고 싶을 때 퍼미션을 설정합니다.

퍼미션을 설정하고 권한 정의, 기능 제공 하는 쪽 앱은 **<permission>** 태그를 활용합니다.

퍼미션으로 보호된 기능을 사용하려는 앱은 **<uses-permission>** 태그를 사용합니다.

- **<permission>** : 기능을 보호하려는 앱의 매니페스트 파일에 설정합니다.
- **<uses-permission>** : 퍼미션으로 보호된 기능을 사용하려는 앱의 매니페스트 파일에 설정합니다.
