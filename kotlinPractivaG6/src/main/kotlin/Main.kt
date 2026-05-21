package com.tuempresa//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   // val estudiante = listOf("Geordany", "Yaritza", "Eric", "Carlos")
    val estudiante : List<String> = listOf("Geordany", "Yaritza", "Eric", "Carlos")
    //var carrera = mutableListOf("Sistema", "Arquitectura", "Civil")
    var estudiante2 = estudiante.toMutableList()
    //carrera.add("Industrial")
    /*for(e in estudiante) {
        println("$e")
    }*/
    estudiante.forEach {println(it)}
    estudiante.forEachIndexed { index, s -> println("Indice $index -> $s")  }
    estudiante.filter { it.startsWith("G") }

    val carrera : Map<Int, String> = mapOf(1 to "Arquitectura", 2 to "Ing. en Sistema", 3 to "Ing, Industrial")
    /*for(e in carrera.values){
        println("$e")
    }*/
    carrera.forEach {(t, u) -> "$t, $u" }
}
