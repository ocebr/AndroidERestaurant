package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.databinding.LoginFragmentBinding
import fr.isen.bras.androiderestaurant.model.DishResult
import fr.isen.bras.androiderestaurant.model.Identifiant
import fr.isen.bras.androiderestaurant.model.LoginResult
import org.json.JSONObject


class LoginFragment  : Fragment (R.layout.login_fragment){

    private lateinit var binding: LoginFragmentBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.donthaveaccount.setOnClickListener(){
            (activity as ConnectionActivity)?.changeFragmentToSignUp()
        }
        binding.valider.setOnClickListener(){
            val mail = binding.adressemailconnect.editText?.text.toString()
            val mdp = binding.motdepasseconnect.editText?.text.toString()

            if(isInputValid(mdp)  && isEmailValid(mail)) {
                connect( mail, mdp)

            }
            else{
                Toast.makeText(context, "Champs invalides", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun connect( mail: String, mdp : String){


        val queue = Volley.newRequestQueue(context)
        val url = "http://test.api.catering.bluecodegames.com/user/login"
        val jsonObject = JSONObject()

        jsonObject.put("id_shop", "1")
        jsonObject.put("email",mail)
        jsonObject.put("password", mdp)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val gson = Gson()
                val id = gson.fromJson(response.toString(), LoginResult::class.java)
                Log.d("identifiant","$id")

                Log.d("", "$response")
                (activity as ConnectionActivity)?.saveId(id.data.email)

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


    fun isEmailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
    fun isInputValid(name: String) :Boolean {
        return name.length > 8

    }

}