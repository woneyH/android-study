/**
 * 핵심
 * 1. 상속할 수 있게 open 키워드 사용한다.
 * 2. 상속받은 클래스는 부모의 생성자를 반드시 호출해야한다. (최소 1개 호출)
 * 3. 자식에게 주 생성자가 없을 때는 constructor():super()  super 키워드로 부모 생성자 호출
 */
//매개변수가 없는 부모 생성자 호출
open class Parent {
    private var name: String = ""
}

class Child1 : Parent() {

}

open class Parent2(private val number: Long) {
    init {
        println("number : $number")
    }
}

//자식에 주 생성자가 존재하면 주 생성자 옆에 부모 생성자 호출
//super() 못씀
class Child2(val number: Long) : Parent2(number) {

}

//보조 생성자로 상위 클래스 생성자 호출
class Child3 : Parent2 {
    constructor(number: Long) : super(number) {

    }
}

val test1: Child3 = Child3(50)//매개변수가 없는 부모 생성자 호출

open class Parent {
    private var name: String = ""
}

class Child1 : Parent() {

}

open class Parent2(private val number: Long) {
    init {
        println("number : $number")
    }
}

//자식에 주 생성자가 존재하면 주 생성자 옆에 부모 생성자 호출
//super() 못씀
class Child2(val number: Long) : Parent2(number) {

}

//보조 생성자로 상위 클래스 생성자 호출
class Child3 : Parent2 {
    constructor(number: Long) : super(number) {

    }
}

val test1: Child3 = Child3(50)
