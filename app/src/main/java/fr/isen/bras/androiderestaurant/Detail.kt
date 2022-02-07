package fr.isen.bras.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bras.androiderestaurant.model.DishModel
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.bras.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.SavedDishInBasket
import java.io.File


class Detail : MenuActivity() {

    @SuppressLint("SetTextI18n")
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemDish = intent.getSerializableExtra("itemDish") as DishModel

        var quantity = 1




        //carrousel image
        val imgs: List<String> = itemDish.images
        val adapter = ViewPagerAdapter(this, imgs)
        binding.viewPager.adapter = adapter


        //title display
        binding.detailtitle.setText(itemDish.name_fr)


        //ingredients display
        for (i in itemDish.ingredients) binding.detailtext.append(i.name_fr + " ")

        //price display
        binding.detailprice.text = "Total   " + itemDish.prices[0].price + "€"

        //quantity display
        binding.quantity.text = "$quantity"


        //click listener

        binding.plus.setOnClickListener {
            quantity++

            val totalprice = (itemDish.prices[0].price.toFloat()) * quantity.toFloat()
            binding.detailprice.text = "Total   " + "$totalprice" + "€"
            binding.quantity.text = "$quantity"


        }


        binding.moins.setOnClickListener() {
            if (quantity > 1) quantity--
            else quantity = 1

            val totalprice: Float = (itemDish.prices[0].price.toFloat()) * quantity
            binding.detailprice.text = "Total   " + "$totalprice" + "€"
            binding.quantity.text = "$quantity"


        }

        binding.detailprice.setOnClickListener() {
            val itemtoadd = DishBasket(itemDish, quantity)
            val filename = "/basket.json"
            val file = File(cacheDir.absolutePath + filename)
            var quantityalreadyinbasket:Int =0
            var namealeradyinbasket :String =""
            var notinbasket:Boolean = false
            Snackbar.make(it, "Ajouté au panier", Snackbar.LENGTH_LONG).show()

            if (file.exists()) {
                var dishbasket = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)


                for (item in dishbasket.list) {
                    if (item.itemdish.name_fr == itemtoadd.itemdish.name_fr) {
                        quantityalreadyinbasket = itemtoadd.quantity + item.quantity
                        namealeradyinbasket = item.itemdish.name_fr
                        notinbasket=false
                    } else {
                         notinbasket=true
                    }
                }

                if(notinbasket==true) dishbasket.list.add(itemtoadd)

                dishbasket.list.forEach { if (it.itemdish.name_fr== namealeradyinbasket ) it.quantity = quantityalreadyinbasket}

                file.writeText(Gson().toJson(SavedDishInBasket(dishbasket.list)))

            }


            else {
                file.writeText(Gson().toJson(SavedDishInBasket(arrayListOf(itemtoadd))))
                //}
                //back button

            }
        }

        binding.detailback.setOnClickListener {
            finish()
        }
    }
}