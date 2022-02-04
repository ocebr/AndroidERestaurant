package fr.isen.bras.androiderestaurant.model

import java.io.Serializable

data class LoginResult(val data: Identifiant): Serializable

data class Identifiant(val email : String) : Serializable
