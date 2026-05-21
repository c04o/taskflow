package com.tuempresa

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    val anio = 2026
    var op1 = 1
    var op2 = 2
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("El resultado de la suma es .${sumar(op1, op2)}")
    println("La multiplicacion del resultado es . ${multiplicar(op1, op2)}")
    println("El resultado de la division es . ${division(op1, op2)}")

}
fun saludar(name: String) : String = "Hello $name!"
}

fun sumar(a: Int, b: Int): Int = a + b

fun multiplicar(a: Int, b: Int): Int = a * b

fun division(a: Double, b: Double): Double {
    if (a == 0.0) return 0.0
    return a/b
}