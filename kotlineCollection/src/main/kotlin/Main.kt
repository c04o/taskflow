package com.tuempresa

import java.time.ZonedDateTime

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(arg: Array<String>) {
    val a = 3; val b = 5
    println("El resultado es ${calcular(a, b,"+")}")
    evaluar()
}

fun calcular(a: Int, b: Int, op: String) : Int {
    return when (op) {
        "+" -> a + b
        "*" -> a * b
        "-" -> a - b
        "/" -> {
            if(b == 0) 0
else a/b}
        else -> 0
    }
}

fun evaluar(){
    var opcion = 0
    while (opcion != 5){
        println("1, sumar")
        println("2, restar")
        println("3, multiplicar")
        println("4, dividir")
        println("5, salir")

        opcion = readln() . toInt()
        when (opcion) {
            1 -> println("El resultado de la suma es $opcion")
            2 -> println("La hora es : ${ZonedDateTime.now()}")
            3 -> println("El resultado es:")
        }
    }
}