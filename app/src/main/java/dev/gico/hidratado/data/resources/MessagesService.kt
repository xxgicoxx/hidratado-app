package dev.gico.hidratado.data.resources

import android.content.Context
import dev.gico.hidratado.R
import java.util.Random

class MessagesService(private val context: Context) {

    fun getMessage(): String {
        val waterFactsArray = context.resources.getStringArray(R.array.water_facts)
        val random = Random()

        return waterFactsArray[random.nextInt(waterFactsArray.size)]
    }
}
