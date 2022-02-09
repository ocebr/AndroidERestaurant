package fr.isen.bras.androiderestaurant


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.isen.bras.androiderestaurant.databinding.FragmentOrderSuccessedBinding



class OrderSuccessedFragment  : Fragment (R.layout.fragment_order_successed){

    private lateinit var binding: FragmentOrderSuccessedBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderSuccessedBinding.inflate(layoutInflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backhome.setOnClickListener{
            (activity as OrderActivity)?.backHome()
        }

    }
}





