package fr.isen.bras.androiderestaurant

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncSharedPreferences() {
    private lateinit var sharedPreferences: SharedPreferences

    private fun initializeEncSharedPreferences(context: Context) {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

         sharedPreferences = EncryptedSharedPreferences.create(
            "IdSaving",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun editPref(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getPref(key: String): String? {

        return  sharedPreferences.getString(key,"")
    }

    companion object {
        private var mInstance: EncSharedPreferences? = null
        fun getInstance(context: Context): EncSharedPreferences {
            if(mInstance == null) {
                mInstance = EncSharedPreferences()
                mInstance?.initializeEncSharedPreferences(context)
            }
            return mInstance as EncSharedPreferences
        }
    }

}