package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import fr.isen.bras.androiderestaurant.databinding.ActivityConnectionBinding
import fr.isen.bras.androiderestaurant.model.LoginResult


class ConnectionActivity : MenuActivity(){

    private lateinit var binding: ActivityConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,SignUpFragment()).commit()

        binding.back.setOnClickListener {
            finish()
        }

        val id_user = getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("mail","").toString()
        if(id_user!=""){
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView,BlankFragment()).commit()
        }

    }

    fun changeFragmentToLogin(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,LoginFragment()).commit()

    }

    fun changeFragmentToSignUp(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,SignUpFragment()).commit()
    }

    fun saveId (id : String){
        val sharedPreferences = getSharedPreferences("IdSaving", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("mail", id)
        editor.apply()
        editor.commit()



        Log.d("recup file",sharedPreferences.getString("mail","").toString())
    }

}