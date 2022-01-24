package fr.isen.bras.androiderestaurant

import android.content.Intent
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
        val tvMainCourse = findViewById<TextView>(R.id.Maincourse)
        val tvDeserts = findViewById<TextView>(R.id.Deserts)


        // set on-click listener
        tvStarter.setOnClickListener {
            val str = tvStarter.getText()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Starters.", Toast.LENGTH_LONG).show()
            //Log.w("home activity", "je suis passé par ici")
            val monIntent : Intent =  Intent(this,SelectedCategoryActivity::class.java)
            monIntent.putExtra("selectedCategory", str)
            startActivity(monIntent)
        }
        tvMainCourse.setOnClickListener {
            val str = tvMainCourse.getText()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Main courses.", Toast.LENGTH_LONG).show()
            //Log.w("home activity", "je suis passé par ici")
            val monIntent : Intent =  Intent(this,SelectedCategoryActivity::class.java)
            monIntent.putExtra("selectedCategory", str)
            startActivity(monIntent)
        }
        tvDeserts.setOnClickListener {
            val str = tvDeserts.getText()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Desert.", Toast.LENGTH_LONG).show()
            //Log.w("home activity", "je suis passé par ici")
            val monIntent : Intent =  Intent(this,SelectedCategoryActivity::class.java)
            monIntent.putExtra("selectedCategory", str)
            startActivity(monIntent)


        }









    }

}
