package fr.isen.bras.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import fr.isen.bras.androiderestaurant.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var str: String? = ""
        if (intent.hasExtra("title")) {
            str = intent.getStringExtra("title")
        }
        val detail_title = findViewById<TextView>(R.id.detailtitle)
        detail_title.setText(str)

        val back =findViewById<Button>(R.id.detailback)
        back.setOnClickListener {
            finish()
        }


    }
}