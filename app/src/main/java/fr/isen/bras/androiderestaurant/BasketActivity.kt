package fr.isen.bras.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val lu = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)
        if(lu.list.size ==0) binding.order.setVisibility(View.INVISIBLE)

        displayBasket(lu.list)
        getTotalPrice(lu)
        getItemCount(lu)

        binding.order.setOnClickListener {

            val id_user =
                getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("mail", "")
                    .toString()
            if (id_user != "") {
                val monIntent= Intent(this, ConnectionActivity::class.java)
                monIntent.putExtra("id_user", id_user)
                startActivity(monIntent)
            } else {
                val monIntent= Intent(this, ConnectionActivity::class.java)
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

        val adapter = CustomAdapterForBasket(dishBasket,this)

        recyclerview.adapter = adapter
    }

    override fun onCellClickListener(data: DishModel) {
    }

    override fun onCellClickListenerBasketRemove(data: DishBasket) {

        val filename = "/basket.json"
        val file = File(cacheDir.absolutePath + filename)
        val lu = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)
        lu.list.remove(
            DishBasket(data.itemdish,data.quantity)
        )
        file.writeText(Gson().toJson(SavedDishInBasket(lu.list)))
        displayBasket(lu.list)
        getItemCount(lu)
        if(lu.list.size ==0) binding.order.setVisibility(View.INVISIBLE)

}

    override fun onCellClickListenerBasketPlusOrMinus(data: DishBasket, value: String) {
        if(value =="plus") {
            val filename = "/basket.json"
            val file = File(cacheDir.absolutePath + filename)
            val lu = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)


            lu.list.remove(DishBasket(data.itemdish,data.quantity))
            data.quantity+=1
            lu.list.add(DishBasket(data.itemdish,data.quantity))
            file.writeText(Gson().toJson(SavedDishInBasket(lu.list)))
            displayBasket(lu.list)
            getTotalPrice(lu)
            getItemCount(lu)
        }

        if(value =="moins") {
            val filename = "/basket.json"
            val file = File(cacheDir.absolutePath + filename)
            val lu = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)


            lu.list.remove(DishBasket(data.itemdish,data.quantity))
            data.quantity-=1
            lu.list.add(DishBasket(data.itemdish,data.quantity))
            file.writeText(Gson().toJson(SavedDishInBasket(lu.list)))
            displayBasket(lu.list)
            getTotalPrice(lu)
            getItemCount(lu)
        }

    }

    fun getTotalPrice(lu:SavedDishInBasket){
        var totalprice :Float =0F
        lu.list.forEach { totalprice+= it.quantity.toFloat()*it.itemdish.prices[0].price.toFloat() }
        binding.order.setText("Commander  " + totalprice.toString() +" €")
    }

    @SuppressLint("SetTextI18n")
    fun getItemCount(lu:SavedDishInBasket){
        var totalcount =0
        lu.list.forEach { totalcount+= it.quantity }
        binding.baskettitle.setText("Votre panier : "+ totalcount.toString())
    }



}
