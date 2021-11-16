package com.example.myapplication.cardactivation.data

object MySingleton{

    init {
        println("Singleton class invoked.")
    }
    var variableName = "I am Var"
    var baseUrl=""
    var client_secret=""
    var client_id=""

    fun printVarName(){
        println(variableName)
    }

}