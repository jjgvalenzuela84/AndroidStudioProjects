package com.cursosdedesarrollo.componentesandroidkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_main2.*

class Main2Activity : AppCompatActivity() {

    var mensaje:String =""

    private var dato: String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        try {
            this.mensaje=intent.getStringExtra("Mensaje")
        }catch (e:Exception){
            println("por si acaso")
        }
        textView.setText(this.mensaje)

        //accediendo a los datos de la aplicación
        this.dato=(application as? Aplicacion)?.dato
        Log.d("app:Main2Activity","Dato:"+this.dato)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
