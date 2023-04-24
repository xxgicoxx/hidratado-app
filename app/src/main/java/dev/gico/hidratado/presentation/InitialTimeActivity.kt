package dev.gico.hidratado.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.gico.hidratado.presentation.theme.AppTheme
import dev.gico.hidratado.presentation.ui.time.InitialTime
import dev.gico.hidratado.data.preferences.TimePreferencesService

class InitialTimeActivity : AppCompatActivity() {

    private lateinit var timePreferencesService: TimePreferencesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timePreferencesService = TimePreferencesService(this)

        setContent {
            AppTheme {
                InitialTime(this, timePreferencesService)
            }
        }
    }
}
