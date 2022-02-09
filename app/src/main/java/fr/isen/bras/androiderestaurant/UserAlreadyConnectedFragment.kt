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
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.bras.androiderestaurant.databinding.FragmentUserAlreadyConnectedBinding
import fr.isen.bras.androiderestaurant.databinding.LoginFragmentBinding
import fr.isen.bras.androiderestaurant.model.DishResult
import fr.isen.bras.androiderestaurant.model.Identifiant
import fr.isen.bras.androiderestaurant.model.LoginResult
import org.json.JSONObject


class UserAlreadyConnectedFragment : Fragment (R.layout.login_fragment){

    private lateinit var binding: FragmentUserAlreadyConnectedBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserAlreadyConnectedBinding.inflate(layoutInflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmenttitle.setText("Vous êtes connectés en tant que : "+(activity as ConnectionActivity)?.getUserId()+". Voulez vous continuer avec ce compte ?")
        binding.no.setOnClickListener{
            (activity as ConnectionActivity)?.changeFragmentToLogin()
        }
        binding.yes.setOnClickListener{
            (activity as ConnectionActivity)?.redirectToOrder()
        }
    }








}