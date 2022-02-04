package fr.isen.bras.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.isen.bras.androiderestaurant.databinding.LoginFragmentBinding


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
    }

}