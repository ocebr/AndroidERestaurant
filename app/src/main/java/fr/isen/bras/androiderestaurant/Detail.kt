package fr.isen.bras.androiderestaurant

import ItemsViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import fr.isen.bras.androiderestaurant.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var item: String? = ""


        val itemDish= intent.getSerializableExtra("itemDish") as ItemsViewModel



        val detail_title = findViewById<TextView>(R.id.detailtitle)
        detail_title.setText(itemDish.text)
        val detail_image = findViewById<ImageView>(R.id.detailImage)
        detail_image.setImageResource(itemDish.image)
        val detail_detail = findViewById<TextView>(R.id.detailDetail)
        detail_detail.setText(itemDish.detail)





        val back =findViewById<Button>(R.id.detailback)
        back.setOnClickListener {
            finish()
        }


    }
}