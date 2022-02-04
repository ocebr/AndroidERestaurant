package fr.isen.bras.androiderestaurant

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.databinding.SignupFragmentBinding
import fr.isen.bras.androiderestaurant.model.DishResult
import org.json.JSONObject


class SignUpFragment : Fragment(R.layout.signup_fragment){

    private lateinit var binding: SignupFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SignupFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alreadyhaveaccount.setOnClickListener{
            (activity as? ConnectionActivity)?.changeFragmentToLogin()
        }
        binding.enregistrer.setOnClickListener {
            val nom = binding.Nominput.editText?.text.toString()
            val prenom = binding.prenominput.editText?.text.toString()
            val mail = binding.adressemailinput.editText?.text.toString()
            val mdp = binding.motdepasseinput.editText?.text.toString()
            val adresse = binding.adresse.editText?.text.toString()

            if(isInputValid(nom) && isInputValid(prenom) && isInputValid(mdp) &&isInputValid(adresse) && isEmailValid(mail)) {
                createaccount(nom, prenom, mail, mdp, adresse)

            }
            else{
                Toast.makeText(context, "Champs invalides",Toast.LENGTH_LONG).show()
            }

        }



    }

    private fun createaccount(nom : String, prenom : String, mail: String, mdp : String, adresse: String){


        val queue = Volley.newRequestQueue(context)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val jsonObject = JSONObject()

        jsonObject.put("id_shop", "1")
        jsonObject.put("email",mail)
        jsonObject.put("firstname", prenom)
        jsonObject.put("address", adresse)
        jsonObject.put("lastname", nom)
        jsonObject.put("password", mdp)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->

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

    fun isEmailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
    fun isInputValid(name: String) :Boolean {
        return name.length > 8

    }




}