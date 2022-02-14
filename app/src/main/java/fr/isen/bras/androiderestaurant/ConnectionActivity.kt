package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.bras.androiderestaurant.databinding.ActivityConnectionBinding



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
            fragmentTransaction.replace(R.id.fragmentContainerView,UserAlreadyConnectedFragment()).commit()

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

    fun saveId (id : String, firstname : String, lastname: String){
        val sharedPreferences = getSharedPreferences("IdSaving", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("id_user", id)
        editor.putString("firstname", firstname)
        editor.putString("lastname", lastname)
        editor.apply()
        editor.commit()
    }
    fun redirectToOrder(){
        val monIntent =  Intent(this,OrderActivity::class.java)
        startActivity(monIntent)

    }

    fun getUser() : String{

        var lastname = getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("lastname","").toString()

        var firstname  = getSharedPreferences("IdSaving", Context.MODE_PRIVATE).getString("firstname","").toString()

        var name = "$lastname"+ " $firstname"

        return name
    }







}