package class_pracvice

fun main(args: Array<String>) {
    val c1: Car = Car(2023, "Hyundai", "black")
    val c2 = Car("white")
    val c3 = Car("black")
    val c4 = Car(2021,"yellow")
}


class Car(age: Int, name: String, color: String) {
    init {
        println("이름: ${name} 연식: ${age}")
    }

    constructor(color: String) : this(2023, "Hyundai", color) {
        println("call secondary")
    }

    constructor(a : Int, c : String) : this(a,"name",c)
}