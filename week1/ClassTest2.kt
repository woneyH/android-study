package class_pracvice

import java.util.Scanner

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)
    print("나이와 이름은 > ")
    val text = sc.nextLine()
    val t1: Test1 = Test1(text)
    t1.callInfo()
}

class Test1(val name: String, val age: Int) {
    init {
        println("name = $name age = $age")
    }

    constructor(text: String) : this(
        name = text.split(",")[0],
        age = text.split(",")[1].toInt()
    )

    fun callInfo(): Unit {
        println("이름은: ${name} 나이는 ${age}")
    }
}