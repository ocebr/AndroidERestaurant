package fr.isen.bras.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.isen.bras.androiderestaurant.databinding.FragmentOrderLoadingBinding



class OrderLoadingFragment  : Fragment (R.layout.fragment_order_loading){

    private lateinit var binding: FragmentOrderLoadingBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderLoadingBinding.inflate(layoutInflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        }
    }





