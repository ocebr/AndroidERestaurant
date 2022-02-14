package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.SignupFragmentBinding
import fr.isen.bras.androiderestaurant.model.LoginResult

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
import com.google.gson.Gson
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
            val mdp1 = binding.pwd.text.toString()
            val mdp2 = binding.pwdagain.text.toString()
            val adresse = binding.adresse.editText?.text.toString()

            if(isInputValid(nom) && isInputValid(prenom) && isPasswordValid(mdp1) && isAddressValid(adresse) && isEmailValid(mail)
                && isPasswordValid(mdp2 ) && mdp1==mdp2) {
                createaccount(nom, prenom, mail, mdp1, adresse)
            }
            else{
                if(mdp1!=mdp2 ) {
                    binding.pwd.setTextColor(Color.RED)
                    binding.pwdagain.setTextColor(Color.RED)
                }
                if(!isEmailValid(mail)) binding.adressemailinput.setBackgroundColor(Color.RED)
                if(!isAddressValid(adresse)) binding.adresse.setBackgroundColor(Color.RED)
                Toast.makeText(context, "Champs invalides", Toast.LENGTH_LONG).show()
            }
        }
        binding.showhide.setOnClickListener {
            if(binding.showhide.text.toString().equals("Montrer")){
                binding.pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showhide.text = "Cacher"
            } else
            {
                binding.pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showhide.text = "Montrer"
            }
        }
        binding.showhide2.setOnClickListener {
            if(binding.showhide2.text.toString().equals("Montrer")){
                binding.pwdagain.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.showhide2.text = "Cacher"
            } else{
                binding.pwdagain.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.showhide2.text = "Montrer"
            }
        }
    }

    private fun createaccount(nom : String, prenom : String, mail: String, mdp : String, adresse: String) {

        val queue = Volley.newRequestQueue(context)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val jsonObject = JSONObject()

        jsonObject.put("id_shop", "1")
        jsonObject.put("email", mail)
        jsonObject.put("firstname", prenom)
        jsonObject.put("address", adresse)
        jsonObject.put("lastname", nom)
        jsonObject.put("password", mdp)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val httpanswer = Gson().fromJson(response.toString(), LoginResult::class.java)
                if (httpanswer.code == "200") (activity as ConnectionActivity)?.changeFragmentToLogin()
            }, {
                // Error in request
                Log.i("", "Volley error: $it")
            })

        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0,
            1f
        )
        queue.add(request)
    }

    fun isEmailValid(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
    fun isInputValid(name: String) :Boolean {
        return name.length >=2
    }
    fun isAddressValid (name: String) :Boolean {
        return name.length >=5
    }
    fun isPasswordValid(name: String) :Boolean {
        return name.length >= 6
    }
}