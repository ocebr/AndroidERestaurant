package fr.isen.bras.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.bras.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : MenuActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tvStarter = binding.starters
        val tvMainCourse = binding.Maincourse
        val tvDesserts = binding.Desserts

        // set on-click listener
        tvStarter.setOnClickListener {
            val str : String = tvStarter.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Starters.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)

        }
        tvMainCourse.setOnClickListener {
            val str: String = tvMainCourse.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Main courses.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)

        }
        tvDesserts.setOnClickListener {
            val str :String = tvDesserts.getText().toString()
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@HomeActivity, "You clicked on Dessert.", Toast.LENGTH_SHORT).show()
            //Log.w("home activity", "je suis passé par ici")
            Log.i("info","end of Home Activity")
            changeActivityWithCategory(str)
        }
    }
    private fun changeActivityWithCategory(str : String) {

        val monIntent : Intent =  Intent(this,SelectedCategoryActivity::class.java)
        monIntent.putExtra("selectedCategory", str)
        startActivity(monIntent)
    }
}


