package fr.isen.bras.androiderestaurant
import fr.isen.bras.androiderestaurant.databinding.FragmentUserAlreadyConnectedBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class UserAlreadyConnectedFragment : Fragment (R.layout.login_fragment){

    private lateinit var binding: FragmentUserAlreadyConnectedBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserAlreadyConnectedBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = (activity as ConnectionActivity)?.getUser()

        binding.fragmenttitle.setText("Vous êtes connectés en tant que : "+"$name"+". Voulez vous continuer avec ce compte ?")
        binding.no.setOnClickListener{
            (activity as ConnectionActivity)?.changeFragmentToLogin()
        }
        binding.yes.setOnClickListener{
            (activity as ConnectionActivity)?.redirectToOrder()
        }
    }
}