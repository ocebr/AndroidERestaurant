package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.ActivityUserPreviousOrdersBinding
import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.OrderResults
import fr.isen.bras.androiderestaurant.model.PreviousOrdersResult

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class UserPreviousOrdersActivity : MenuActivity(){
    private lateinit var binding: ActivityUserPreviousOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPreviousOrdersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getOrders()
        binding.back.setOnClickListener{
            finish()
        }
    }

    private fun getOrders(){

        val id_user = getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("id_user","").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/listorders"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        jsonObject.put("id_user",id_user)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->

                Log.d("commandes","$response")
                val httpanswer = Gson().fromJson(response.toString(), PreviousOrdersResult::class.java)


                val itemType = object : TypeToken<List<DishBasket>>() {}.type
                httpanswer.data.forEach { it.messageList = Gson().fromJson(it.message, itemType)}
                displayPreviousOrders(httpanswer.data)

            },
            {
                Log.i("","Volley error: $it")

            })
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0,
            1f
        )
        queue.add(request)
    }

    private fun displayPreviousOrders(List: List <OrderResults>){

        val recyclerview = binding.listoforders
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapterForPreviousOrders(List)
        recyclerview.adapter = adapter
    }
}