package com.example.myapplication.cardactivation.data

object MySingleton{

    init {
        println("Singleton class invoked.")
    }
    var variableName = "I am Var"
    var baseUrl=""
    var client_secret=""
    var client_id=""
    var service_name=""

    fun printVarName(){
        println(variableName)
    }

}