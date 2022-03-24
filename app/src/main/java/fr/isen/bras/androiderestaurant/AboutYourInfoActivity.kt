package fr.isen.bras.androiderestaurant


import android.os.Bundle
import fr.isen.bras.androiderestaurant.databinding.ActivityAboutYourInfoBinding



class AboutYourInfoActivity : MenuActivity() {

    private lateinit var binding: ActivityAboutYourInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutYourInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.back1.setOnClickListener {
            finish()
        }
    }

}