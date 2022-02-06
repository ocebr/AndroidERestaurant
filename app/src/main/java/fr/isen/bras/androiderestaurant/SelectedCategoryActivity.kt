package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.ActivitySelectedCategoryBinding

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


import android.util.Log
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.model.DishModel
import fr.isen.bras.androiderestaurant.model.DishResult



class SelectedCategoryActivity : MenuActivity(), CellClickListener {

    private lateinit var binding: ActivitySelectedCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)






        binding.back.setOnClickListener {
            finish()
        }

        var category: String? = ""
        if (intent.hasExtra("selectedCategory")) {
            category = intent.getStringExtra("selectedCategory")
        }
        val textViewCategory = binding.category
        textViewCategory.setText(category)



        //http request to the API
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")

        // Request a string response from the provided URL.
        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->

                var gson = Gson()
                var dishresult = gson.fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishresult.data.firstOrNull { it.name_fr == category }?.items ?: listOf())


                Log.d("", "$response")
            }, {
                // Error in request
                Log.i("","Volley error: $it")
            })

        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        queue.add(request)


    }

    private fun displayDishes (dishresult: List<DishModel>){
        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)


        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapterForDishDisplayByCategory(dishresult, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


    }


    override fun onCellClickListener(data: DishModel) {
        val monIntent : Intent =  Intent(this,Detail::class.java)
        monIntent.putExtra("itemDish", data)
        startActivity(monIntent)
    }








}

