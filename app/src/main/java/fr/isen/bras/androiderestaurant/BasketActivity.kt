package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.DishModel
import fr.isen.bras.androiderestaurant.model.SavedDishInBasket
import java.io.File


class BasketActivity : AppCompatActivity(), CellClickListener {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val filename = "/basket.json"
        val file = File(cacheDir.absolutePath + filename)


        if (file.exists())
        {


            val lu = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)
            displayBasket(lu.list)


        }

        binding.order.setOnClickListener {

            val id_user =
                getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("mail", "")
                    .toString()

            if (id_user != "") {
                val monIntent: Intent = Intent(this, ConnectionActivity::class.java)
                monIntent.putExtra("id_user", id_user)
                startActivity(monIntent)
            } else {
                val monIntent: Intent = Intent(this, ConnectionActivity::class.java)
                startActivity(monIntent)

            }
        }


        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun displayBasket(dishBasket: ArrayList<DishBasket>) {

        val recyclerview = binding.listinbasket

        recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = CustomAdapterForBasket(dishBasket)

        recyclerview.adapter = adapter


    }

    override fun onCellClickListener(data: DishModel) {

    }

    override fun onCellClickListenerBasket() {


    }


}