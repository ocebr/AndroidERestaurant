package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import com.google.android.material.snackbar.Snackbar
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
            val mdp = binding.pwd.text.toString()

            if(isPasswordValid(mdp)  && isEmailValid(mail)) {
                connect( mail, mdp)
            }
            else{
                if(!isPasswordValid(mdp)) binding.pwd.setTextColor(Color.RED)
                if(!isEmailValid(mail)) binding.adressemailconnect.setBackgroundColor(Color.RED)

                Toast.makeText(context, "Champs invalides", Toast.LENGTH_LONG).show()
            }
        }

        binding.showhide.setOnClickListener {
            if(binding.showhide.text.toString().equals("Montrer")){
                binding.pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showhide.text = "Cacher"
            } else{
                binding.pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showhide.text = "Montrer"
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

                val httpanswer = Gson().fromJson(response.toString(), LoginResult::class.java)

                //save user id into shared preferences
                (activity as ConnectionActivity)?.saveId(httpanswer.data.id,httpanswer.data.firstname,httpanswer.data.lastname)

                if(httpanswer.code=="200")  {
                    (activity as ConnectionActivity)?.redirectToOrder()

                }



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
    fun isPasswordValid(name: String) :Boolean {
        return name.length >= 6

    }

}