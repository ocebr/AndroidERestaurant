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
import fr.isen.bras.androiderestaurant.model.DishBasket
import java.io.File


class Detail : MenuActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemDish= intent.getSerializableExtra("itemDish") as DishModel

        var quantity = 1


        val detail_title = findViewById<TextView>(R.id.detailtitle)
        val buttonmoins =findViewById<FloatingActionButton>(R.id.moins)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val detail_text = findViewById<TextView>(R.id.detailtext)
        val quantitydiplay =findViewById<TextView>(R.id.quantity)
        val pricedisplay = findViewById<TextView>(R.id.detailprice)
        val buttonplus =findViewById<FloatingActionButton>(R.id.plus)
        val back =findViewById<Button>(R.id.detailback)

        //carrousel image
        val imgs: List <String> = itemDish.images
        val adapter = ViewPagerAdapter(this, imgs)
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
            val itemtoadd = DishBasket(itemDish,quantity)
            val filename = "/basket.json"
            val file :File = File(cacheDir.absolutePath + filename)


            Snackbar.make(it,"Ajouté au panier", Snackbar.LENGTH_LONG).show()
            var dishbasket: List<DishBasket> =ArrayList<DishBasket>()

            if (file.exists()) {
                dishbasket= Gson().fromJson(file.readText(), List::class.java) as List<DishBasket>
            }



            //dishbasket.forEach { if(it.itemdish.name_fr == itemtoadd.itemdish.name_fr) it.quantity += itemtoadd.quantity  }

            //dishbasket+=itemtoadd


            file.writeText(Gson().toJson(dishbasket))

            val monIntent : Intent =  Intent(this,BasketActivity::class.java)
            startActivity(monIntent)

            }











        //back button
       back.setOnClickListener {
            finish()
        }






    }
}