package fr.isen.bras.androiderestaurant.model
import java.io.Serializable

data class SavedDishInBasket (val list : ArrayList<DishBasket>) :Serializable
data class DishBasket(val itemdish : DishModel, var quantity : Int): Serializable