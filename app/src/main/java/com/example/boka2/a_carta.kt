package com.example.boka2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.p_carta.*

class a_carta : AppCompatActivity() {
    //Barra de tareas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        if (Sharedapp.prefs.tipousu.equals("invitado")){
            inflater.inflate(R.menu.menuinvitado, menu)
        }else if (Sharedapp.prefs.tipousu.equals("admin")){
            inflater.inflate(R.menu.menuadmin, menu)
        }
        else{
            inflater.inflate(R.menu.menu, menu)
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()

        if (iditem==R.id.carta){
            Toast.makeText(this, "${getResources().getString(R.string.pagina_actual)}", Toast.LENGTH_SHORT).show()
        }
        if (iditem==R.id.Localizar){
            if (Sharedapp.prefs.tipousu.equals("admin")){
                val intent= Intent(this, a_localizaradmin::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_localizacion::class.java)
                finish()
                startActivity(intent)
            }

        }
        if (iditem==R.id.Reservar){
            val intent= Intent(this, a_reservas::class.java)
            finish()
            startActivity(intent)
        }
          if (iditem==R.id.Calendario){
               val intent=Intent(this, a_calendario::class.java)
              finish()
               startActivity(intent)
           }
        if (iditem==R.id.Quienes){
            val intent= Intent(this, a_quienesSomos::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Perfil){
            if (!Sharedapp.prefs.tipousu.equals("invitado")){
                val intent= Intent(this, a_perfil::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            }

        }
        if (iditem==R.id.sesion) {

            if (!Sharedapp.prefs.tipousu.equals("invitado")) {
                Toast.makeText(
                    this,
                    "${getResources().getString(R.string.cierre_sesion)}",
                    Toast.LENGTH_SHORT
                ).show()
                Sharedapp.prefs.tipousu = "invitado"
                Sharedapp.user.user = ""
                Sharedapp.paswd.paswd = ""
                val intent = Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            } else {
                val intent = Intent(this, a_registro::class.java)
                Sharedapp.prefs.tipousu = "invitado"
                finish()
                startActivity(intent)
            }
        }

        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val BBDDcarta = Base_de_Datos(this, "bd", null, 1 )

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_carta)
        val llBotonera = findViewById<View>(R.id.llcarta) as LinearLayout
        var context:Context?=null
        //Creamos las propiedades de layout que tendrán las imagenes.
        //Son LinearLayout.LayoutParams porque las imagenes van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.MATCH_PARENT,
            //LinearLayout.LayoutParams.WRAP_CONTENT
            //Asignamos un valor fijo para que las imagenes no se deformen
            LinearLayout.LayoutParams(1125, 700)


        )
        val layout: LinearLayout =llcarta

        //Creamos los botones en bucle
        var numBotones = BBDDcarta.Carta("general").size
        for (i in 0 until numBotones) {
            //Cargamos las imagenes de la base de datos y las añadimos a la vista
           var carta:String = BBDDcarta.Carta("general").get(i).nombre
            val resID = resources.getIdentifier(carta, "drawable", packageName)
            context=this
            val img = ImageButton(this)
            lp.setMargins(0,0,0,0)
            img.layoutParams = lp
            img.setBackgroundResource(resID)
            img.tag = "img$i"
            img.setOnClickListener(ButtonsOnClickListener(this))
            llBotonera.addView(img)
        }


        //cargar fotos al cambiar la vista

        tablay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                llBotonera.removeAllViews()
                //En caso de que nos posicionemos sobre carta
                if (tablay.selectedTabPosition==0){
                    var numBotones = BBDDcarta.Carta("general").size
                    for (i in 0 until numBotones) {
                        //Cargamos las imagenes de la base de datos y las añadimos a la vista
                        var carta:String = BBDDcarta.Carta("general").get(i).nombre
                        val resID = resources.getIdentifier(carta, "drawable", packageName)
                        val img = ImageButton(context)
                        lp.setMargins(0,0,0,0)
                        img.layoutParams = lp
                        img.setBackgroundResource(resID)
                        img.tag = "img$i"
                        img.setOnClickListener(ButtonsOnClickListener(this@a_carta))
                        llBotonera.addView(img)
                    }
                //En caso de posicionarnos sobre ofertas
                }else{
                llBotonera.removeAllViews()
                    val img=ImageView(context)
                    var numBotones = BBDDcarta.Evento_ofe("oferta").size
                    for (i in 0 until numBotones) {
                        //Cargamos las imagenes de la base de datos y las añadimos a la vista
                        var carta:String = BBDDcarta.Evento_ofe("oferta").get(i).nombre
                        val resID = resources.getIdentifier(carta, "drawable", packageName)
                        val img = ImageView(context)
                        lp.setMargins(0,30,0,0)
                        img.layoutParams = lp
                        img.setBackgroundResource(resID)
                        llBotonera.addView(img)

                        //img.id = i

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
    //Creamos el listener de la imagen para saber en que clase de comida hemos clicado y cargamos esa clase en un activity nuevo
    internal inner class ButtonsOnClickListener(aCarta: a_carta) : View.OnClickListener {
        override fun onClick(v: View?) {
            val b = v as ImageButton
            when{
                b.tag.equals("img0") -> Sharedapp.tipo.tipo = "ensalada"
                b.tag.equals("img1") -> Sharedapp.tipo.tipo = "fajita"
                b.tag.equals("img2") -> Sharedapp.tipo.tipo = "sandwich"
                b.tag.equals("img3") -> Sharedapp.tipo.tipo = "smoothie"
                b.tag.equals("img4") -> Sharedapp.tipo.tipo = "smoothiebowl"
            }
            val intent= Intent(this@a_carta, a_platos::class.java)
            finish()
            startActivity(intent)
        }
    }


    }

