package fr.isen.bras.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val tvStarter = findViewById<TextView>(R.id.starters)
        // set on-click listener
        tvStarter.setOnClickListener {
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Starters.", Toast.LENGTH_LONG).show()
            //Log.w("home activity", "je suis passé par ici")
        }

    }

}
