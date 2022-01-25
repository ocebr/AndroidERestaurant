package fr.isen.bras.androiderestaurant

import CellClickListener
import CustomAdapter
import ItemsViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager


import fr.isen.bras.androiderestaurant.databinding.ActivitySelectedCategoryBinding

class SelectedCategoryActivity : AppCompatActivity(), CellClickListener {

    private lateinit var binding: ActivitySelectedCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)






        binding.back.setOnClickListener {
            finish()
        }

        var str: String? = ""
        if (intent.hasExtra("selectedCategory")) {
            str = intent.getStringExtra("selectedCategory")
        }
        val textViewCategory = binding.category
        textViewCategory.setText(str)



        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel( R.drawable.pizza,"Item " + i,"detail"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
    override fun onCellClickListener(item: ItemsViewModel) {
        val monIntent : Intent =  Intent(this,Detail::class.java)
        monIntent.putExtra("itemDish", item)



        startActivity(monIntent)
    }




}

