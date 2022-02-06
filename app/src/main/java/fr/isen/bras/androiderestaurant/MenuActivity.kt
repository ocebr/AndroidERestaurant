package fr.isen.bras.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem



open class MenuActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.getItemId()) {
            R.id.basket -> {
                val monIntent : Intent =  Intent(this,BasketActivity::class.java)
                startActivity(monIntent)
                true
            }
            R.id.profil -> {
                val monIntent : Intent =  Intent(this,ConnectionActivity::class.java)
                startActivity(monIntent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }









    }



