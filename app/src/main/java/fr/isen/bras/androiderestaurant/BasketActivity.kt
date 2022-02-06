package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.bras.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.bras.androiderestaurant.databinding.ActivityHomeBinding

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)










        binding.order.setOnClickListener{

            val id_user = getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("mail","").toString()

            if(id_user!=""){
                val monIntent : Intent =  Intent(this,ConnectionActivity::class.java)
                monIntent.putExtra("id_user", id_user)
                startActivity(monIntent)
            }
            else{
                val monIntent : Intent =  Intent(this,ConnectionActivity::class.java)
                startActivity(monIntent)

            }
        }


        binding.back.setOnClickListener {
            finish()
        }
    }
}