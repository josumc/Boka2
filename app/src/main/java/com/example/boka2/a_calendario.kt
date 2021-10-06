package com.example.boka2

import android.os.Bundle
import android.widget.LinearLayout

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.p_calendario.*


class a_calendario : Activity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       val iditem = item.getItemId()

        if (iditem==R.id.carta){
            val intent= Intent(this, a_carta::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.Localizar){
            val intent= Intent(this, a_localizacion::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.Reservar){
            val intent= Intent(this, a_reservas::class.java)
            startActivity(intent)
        }
          if (iditem==R.id.Calendario){
              Toast.makeText(this, "Ya estas en esta pagina", Toast.LENGTH_SHORT).show()
           }
        if (iditem==R.id.Quienes){
            val intent= Intent(this, a_quienesSomos::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.Perfil){
            val intent= Intent(this, a_perfil::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.sesion){
            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, a_login::class.java)
            finish()
            startActivity(intent)
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Establecemos el layout main
        setContentView(R.layout.p_calendario)

        //Obtenemos el linear layout donde colocar los botones
        val llBotonera = findViewById<View>(R.id.llcalendario) as LinearLayout

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams(1125, 700)
        )
            val lp2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,


        )

    val layout:LinearLayout=l_layout

        //Creamos los botones en bucle
        for (i in 0 until numBotones) {
            val img = ImageView(this)
            val txt = TextView(this)
            txt.setLayoutParams(lp2)
            img.setLayoutParams(lp)
            //Asignamos Texto al botón
            txt.setText("Boton " + String.format("%02d", i))
            img.setBackgroundResource(R.drawable.evento)
            //Añadimos el botón a la botonera
            llBotonera.addView(img)
            llBotonera.addView(txt)

        }
    }

    companion object {
        var numBotones = 5
    }
}