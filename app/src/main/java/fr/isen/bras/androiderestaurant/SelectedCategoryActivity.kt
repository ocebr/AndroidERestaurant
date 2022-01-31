package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.ActivitySelectedCategoryBinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


import CellClickListener
import CustomAdapter
import ItemsViewModel


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



        //http request to the API

        val textView = findViewById<TextView>(R.id.httpresponse)
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")


        // Request a string response from the provided URL.
        val request = JsonObjectRequest(
            Request.Method.POST, url,jsonObject,
            Response.Listener { response ->

                try {
                    textView.text = "Response: $response"
                }catch (e:Exception){
                    textView.text = "Exception: $e"
                }




            }, Response.ErrorListener{
                // Error in request
                textView.text = "Volley error: $it"
            })

        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)








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

