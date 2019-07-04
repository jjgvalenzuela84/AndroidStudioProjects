package com.cursosdedesarrollo.componentesandroidkotlin

import android.app.Application
import android.util.Log

class Aplicacion : Application() {
    var dato:String =""
    override fun onCreate() {
        super.onCreate()
        Log.d("app:Applicacion","onCreate")
        this.dato="Mi dato"
    }

}