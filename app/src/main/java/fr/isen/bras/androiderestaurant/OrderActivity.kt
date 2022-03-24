package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.ActivityOrderBinding
import fr.isen.bras.androiderestaurant.model.SavedDishInBasket
import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.OrderResult

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.io.File
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.security.crypto.EncryptedSharedPreferences


class OrderActivity : MenuActivity() {

    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,OrderLoadingFragment()).commit()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            orderFood()
        }, 2000)
    }
    private fun orderFood(){

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/order"
        val jsonObject = JSONObject()
        val id_user = EncSharedPreferences.getInstance(this).getPref("id_user")



        val filename = "/basket.json"
        val file = File(cacheDir.absolutePath + filename)
        var dishbasket = Gson().fromJson(file.readText(), SavedDishInBasket::class.java)

        jsonObject.put("id_shop", "1")
        jsonObject.put("id_user",id_user)
        jsonObject.put("msg",Gson().toJson(dishbasket.list))

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val httpanswer = Gson().fromJson(response.toString(), OrderResult::class.java)
                if(httpanswer.code=="200")  {
                    var emptyBasket: ArrayList<DishBasket> = ArrayList()
                    file.writeText(Gson().toJson(SavedDishInBasket(emptyBasket)))
                    changeToOrderSuccessedFragment()
                }
            },
            {
                // Error in request
            })

        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0,
            1f
        )
        queue.add(request)
    }

    fun changeToOrderSuccessedFragment(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,OrderSuccessedFragment()).commit()
    }
    fun backHome(){

        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        startActivity(intent)
    }
}