package fr.isen.bras.androiderestaurant.model

import java.io.Serializable

data class LoginResult(val data: Identifiant, val code : String): Serializable

data class Identifiant(var id : String, var firstname :String, var lastname : String) : Serializable


