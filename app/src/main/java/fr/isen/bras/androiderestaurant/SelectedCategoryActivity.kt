package fr.isen.bras.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import fr.isen.bras.androiderestaurant.databinding.ActivityHomeBinding

import fr.isen.bras.androiderestaurant.databinding.ActivitySelectedCategoryBinding

class SelectedCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val back = binding.back

        val monIntentRetour : Intent =  Intent(this,HomeActivity::class.java)

        back.setOnClickListener {
            startActivity(monIntentRetour)
        }

        var str: String? = ""
        if (intent.hasExtra("selectedCategory")) {
            str = intent.getStringExtra("selectedCategory")
        }
        val textViewCategory = binding.category
        textViewCategory.setText(str)


    }


}