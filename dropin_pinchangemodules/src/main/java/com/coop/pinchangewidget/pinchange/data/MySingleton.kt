package com.coop.pinchangewidget.pinchange.data

object MySingleton{

    init {
        println("Singleton class invoked.")
    }

    var newPin = ""
    var variableName = "I am Var"
    var baseUrl=""
    var client_secret=""
    var client_id=""
    var service_name=""
    var panNumber= ""

    fun printVarName(){
        println(variableName)
    }

}