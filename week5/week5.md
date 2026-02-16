# 학습 목표

- [ ] 뷰를 배치하는 레이아웃
- [ ] 람다 표현식


---

## 1. 레이아웃 배치 방법들

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



### 1-2 RelativeLayout  상대 위치로 배치

RelativeLayout


### 1-3 FrameLayout  겹쳐서 배치
