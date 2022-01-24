package fr.isen.bras.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SelectedCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_category)

        val back = findViewById<Button>(R.id.back)
        val monIntentRetour : Intent =  Intent(this,HomeActivity::class.java)

        back.setOnClickListener {
            startActivity(monIntentRetour)
        }

        var str: String? = ""
        if (intent.hasExtra("selectedCategory")) {
            str = intent.getStringExtra("selectedCategory")
        }

        val textViewCategory = findViewById<TextView>(R.id.category)
        textViewCategory.setText(str)


    }


}