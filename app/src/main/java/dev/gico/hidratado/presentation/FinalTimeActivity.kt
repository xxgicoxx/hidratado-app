package dev.gico.hidratado.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.gico.hidratado.presentation.theme.AppTheme
import dev.gico.hidratado.presentation.ui.time.FinalTime
import dev.gico.hidratado.service.TimePreferencesService

class FinalTimeActivity : AppCompatActivity() {

    private lateinit var timePreferencesService: TimePreferencesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timePreferencesService = TimePreferencesService(this)

        setContent {
            AppTheme {
                FinalTime(this, timePreferencesService)
            }
        }
    }
}
