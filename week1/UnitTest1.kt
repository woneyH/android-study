// Unit 타입 학습 코드입니다.
// 1. Kotlin에서 Unit은 객체이므로 Parent 인터페이스의 T타입 반환에 Unit이 들어갈 수 있습니다.
// 2. Java에서는 <Void> 를 쓰고, 구현체에 return null;을 반드시 해야 합니다. 하지만 코틀린에서는 Unit을 활용하여 return문을 작성할 필요가 없습니다.
// 3. 결국 Kotlin에서는 Unit이 객체인 것을 해당 코드에서도 확인할 수 있습니다. T <-> Unit  

interface Parent<T> {
    fun play(): T
}

class Child : Parent<Unit> {
    override fun play() {
        println("구현할 때 Unit이므로 반환값이 존재하지 않아도 됨")
    }
}

val c  = Child()
c.play()
