package dev.gico.hidratado.presentation.ui.time

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import dev.gico.hidratado.presentation.components.Timer
import dev.gico.hidratado.data.preferences.TimePreferencesService

@Composable
fun InitialTime(context: Context, timePreferencesService: TimePreferencesService) {

    Timer(
        timePreferencesService.getSelectedInitialTime(),
        onTimeConfirm = {
            timePreferencesService.saveSelectedInitialTime(it)

            (context as Activity).finish()
        }
    )
}
