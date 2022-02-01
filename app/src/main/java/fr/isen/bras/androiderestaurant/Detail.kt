package fr.isen.bras.androiderestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import fr.isen.bras.androiderestaurant.model.DishModel


import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.DishResult
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemDish= intent.getSerializableExtra("itemDish") as DishModel

        var quantity = 1


        val detail_title = findViewById<TextView>(R.id.detailtitle)
        var buttonmoins =findViewById<FloatingActionButton>(R.id.moins)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        val detail_text = findViewById<TextView>(R.id.detailtext)
        var quantitydiplay =findViewById<TextView>(R.id.quantity)
        val pricedisplay = findViewById<TextView>(R.id.detailprice)
        var buttonplus =findViewById<FloatingActionButton>(R.id.plus)
        val back =findViewById<Button>(R.id.detailback)

        //carrousel image
        var imgs: List <String> = itemDish.images
        var adapter = ViewPagerAdapter(this, imgs)
        viewPager.adapter =adapter



        //title display
        detail_title.setText(itemDish.name_fr)


        //ingredients display
        for (i in itemDish.ingredients) detail_text.append(i.name_fr + " ")

        //price display
        pricedisplay.text = "Total   "+itemDish.prices[0].price+ "€"

       //quantity display
        quantitydiplay.text="$quantity"


        //click listener

        buttonplus.setOnClickListener{
            quantity++

            val totalprice = (itemDish.prices[0].price.toFloat())*quantity.toFloat()
            pricedisplay.text = "Total   "+"$totalprice"+ "€"
            quantitydiplay.text="$quantity"


        }


        buttonmoins.setOnClickListener(){
            if (quantity>1) quantity --
            else quantity =1

            val totalprice :Float = (itemDish.prices[0].price.toFloat())*quantity
            pricedisplay.text="Total   "+"$totalprice"+ "€"
            quantitydiplay.text="$quantity"


        }

        pricedisplay.setOnClickListener(){

            val filename = "/basket.json"
            Snackbar.make(it,"Ajouté au panier", Snackbar.LENGTH_LONG).show()
            File(cacheDir.absolutePath + filename).bufferedWriter().use { file->
                file.write(Gson().toJson(DishBasket(itemDish,quantity)))
            }

            val recuperation = File(cacheDir.absolutePath + filename).bufferedReader().readText();

            Gson().fromJson(recuperation,DishBasket::class.java);

            Log.d("panier","$recuperation")

            //Toast.makeText(applicationContext,"data save", Toast.LENGTH_LONG).show()*/
        }


        //back button
       back.setOnClickListener {
            finish()
        }






    }
}