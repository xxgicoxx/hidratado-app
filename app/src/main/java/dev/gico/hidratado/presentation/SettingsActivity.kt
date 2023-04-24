package dev.gico.hidratado.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.gico.hidratado.R
import dev.gico.hidratado.data.domain.MenuHeader
import dev.gico.hidratado.data.domain.MenuItem
import dev.gico.hidratado.presentation.theme.AppTheme
import dev.gico.hidratado.presentation.ui.settings.Settings

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuInitial = MenuItem(0, getString(R.string.initial_time), onClick = { openInitialTimeActivity() })
        val menuFinal = MenuItem(1, getString(R.string.final_time), onClick = { openFinalTimeActivity() })

        menuInitial.secondaryLabel = getString(R.string.set_initial_time)
        menuInitial.icon = R.drawable.ic_play

        menuFinal.secondaryLabel = getString(R.string.set_final_time)
        menuFinal.icon = R.drawable.ic_stop

        val menuHeader = MenuHeader(getString(R.string.settings))
        val menuItems = listOf(menuInitial, menuFinal).toMutableList()

        setContent {
            AppTheme {
                Settings(menuHeader, menuItems)
            }
        }
    }

    private fun openInitialTimeActivity() {
        val intent = Intent(this, InitialTimeActivity::class.java)
        startActivity(intent)
    }

    private fun openFinalTimeActivity() {
        val intent = Intent(this, FinalTimeActivity::class.java)
        startActivity(intent)
    }
}
