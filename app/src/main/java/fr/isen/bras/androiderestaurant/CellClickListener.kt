package fr.isen.bras.androiderestaurant

import fr.isen.bras.androiderestaurant.model.DishBasket
import fr.isen.bras.androiderestaurant.model.DishModel


interface CellClickListener {


    fun onCellClickListener( data: DishModel)
    fun onCellClickListenerBasketRemove(data : DishBasket)
    fun onCellClickListenerBasketPlusOrMinus(data: DishBasket, value: String)


}