package dev.gico.hidratado.presentation.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import com.google.android.horologist.composables.TimePicker
import java.time.LocalTime

@Composable
fun Timer(localTime: LocalTime, onTimeConfirm: (LocalTime) -> Unit) {

    Scaffold(
        Modifier.background(MaterialTheme.colors.background)
    ) {
        TimePicker(
            time = localTime,
            onTimeConfirm = onTimeConfirm,
            showSeconds = false
        )
    }
}
