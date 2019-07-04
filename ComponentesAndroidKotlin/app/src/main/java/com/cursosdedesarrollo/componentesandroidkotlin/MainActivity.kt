package com.cursosdedesarrollo.componentesandroidkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener {


    private val EXTRA_MESSAGE: String? = "Mensaje"
    private var dato:String? =""
    private lateinit var mainFragment:MainActivityFragment
    private lateinit var secondFragment:BlankFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //accediendo a los datos de la aplicación
        this.dato=(application as? Aplicacion)?.dato
        Log.d("app:MainActivity","Dato:"+this.dato)
        (application as? Aplicacion)?.dato="dato modificado"
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Cambia de Activity",  {
                        val intent = Intent(this,Main2Activity::class.java)
                        intent.putExtra(EXTRA_MESSAGE, "Mi Mensaje")
                        this.startActivity(intent)
                    } ).show()
        }
        mainFragment= MainActivityFragment()
        //mainFragment= supportFragmentManager.fragments.get(0) as MainActivityFragment
        cargaPrimera()
        secondFragment = BlankFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings ->{
                cargaSegunda()
                true
            }
            R.id.fragment_1 ->{
                cargaPrimera()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun cargaPrimera(){
        Log.d("app:",supportFragmentManager.fragments.toString())
        supportFragmentManager.beginTransaction()
                //.add(R.id.fragment, mainFragment, "rageComicList")
                //.remove(supportFragmentManager.fragments.get(0))
                .replace(R.id.ReplaceFrame,mainFragment)
                .commit()
        Log.d("app:",supportFragmentManager.fragments.toString())
    }
    fun cargaSegunda() {
        Log.d("app:",supportFragmentManager.fragments.toString())
        supportFragmentManager.beginTransaction()
                //.remove(supportFragmentManager.fragments.get(0))
                //.add(R.id.fragment, secondFragment, "rageComicList")
                .replace(R.id.ReplaceFrame, secondFragment, "rageComicList")
                .commit()
        Log.d("app:",supportFragmentManager.fragments.toString())
    }


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
