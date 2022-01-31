package fr.isen.bras.androiderestaurant.model

import java.io.Serializable

data class DishResult(val data: List<CategoryModel>): Serializable

data class CategoryModel (val name_fr : String, val items: List<DishModel>) : Serializable

data class DishModel (val name_fr: String, val images : List<String>, val prices : List<PriceModel>) : Serializable


data class PriceModel (val price : String)

