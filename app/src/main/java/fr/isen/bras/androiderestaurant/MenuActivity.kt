package fr.isen.bras.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import ru.nikartm.support.ImageBadgeView


open class MenuActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.getItemId()) {
            R.id.cart -> {
                val monIntent =  Intent(this,BasketActivity::class.java)
                startActivity(monIntent)
                true
            }
            R.id.profil -> {
                val monIntent =  Intent(this,UserPreviousOrdersActivity::class.java)
                startActivity(monIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



