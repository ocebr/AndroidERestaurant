package fr.isen.bras.androiderestaurant.model

import java.io.Serializable

data class PreviousOrdersResult(val data : List <Order>) : Serializable

data class Order(val id_sender: String, val message : List<DishBasket>): Serializable