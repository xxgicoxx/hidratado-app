package dev.gico.hidratado.service

import android.content.Context
import java.time.LocalTime

class TimePreferencesService(private val context: Context) {

    fun saveSelectedInitialTime(localTime: LocalTime) {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        editor?.putString("initial_time", localTime.toString())
        editor?.apply()
    }

    fun saveSelectedFinalTime(localTime: LocalTime) {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        editor?.putString("final_time", localTime.toString())
        editor?.apply()
    }

    fun getSelectedInitialTime(): LocalTime {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)
        val initialTime = sharedPref?.getString("initial_time", "00:01:00") ?: "00:01:00"

        return LocalTime.parse(initialTime)
    }

    fun getSelectedFinalTime(): LocalTime {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)
        val initialTime = sharedPref?.getString("final_time", "23:59:00") ?: "23:59:00"

        return LocalTime.parse(initialTime)
    }

    fun saveTime(time: Int) {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        editor?.putInt("selected_time", time)
        editor?.apply()
    }

    fun getTime(): Int {
        val sharedPref = context.getSharedPreferences("hidratado", Context.MODE_PRIVATE)

        return sharedPref?.getInt("selected_time", 15) ?: 15
    }
}
