package fr.isen.bras.androiderestaurant
import android.content.Context
import java.io.File
import java.lang.Exception

class DeviceUtils {

    private fun canExecuteCommand(command: String): Boolean {
        val executedSuccesfully: Boolean
        executedSuccesfully = try {
            Runtime.getRuntime().exec(command)
            true
        } catch (e: Exception) {
            false
        }
        return executedSuccesfully
    }

    fun isDeviceRooted(context: Context?): Boolean {
        return isrooted1() || isrooted2()
    }

    private fun isrooted1(): Boolean {
        val file = File("/system/app/Superuser.apk")
        return if (file.exists()) {
            true
        } else false
    }

    // try executing commands
    private fun isrooted2(): Boolean {
        return (canExecuteCommand("/system/xbin/which su")
                || canExecuteCommand("/system/bin/which su")
                || canExecuteCommand("which su"))
    }
}