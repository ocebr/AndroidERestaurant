package fr.isen.bras.androiderestaurant.model

import java.io.Serializable

data class PreviousOrdersResult(val data : List <OrderResults>) : Serializable

data class OrderResults(val receiver: String, val message : String, var messageList: List<DishBasket>): Serializable