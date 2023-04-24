package dev.gico.hidratado.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.gico.hidratado.R
import dev.gico.hidratado.data.domain.MenuHeader
import dev.gico.hidratado.data.domain.MenuItem
import dev.gico.hidratado.presentation.theme.AppTheme
import dev.gico.hidratado.presentation.ui.menu.MainMenu
import dev.gico.hidratado.data.preferences.TimePreferencesService
import dev.gico.hidratado.utils.NotificationWorkerUtils

class MainActivity : AppCompatActivity() {

    private lateinit var timePreferencesService: TimePreferencesService
    private lateinit var notificationWorkerUtils: NotificationWorkerUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timePreferencesService = TimePreferencesService(this)
        notificationWorkerUtils = NotificationWorkerUtils(this)

        val menuTimer = MenuItem(0, getString(R.string.timer), onClick = { openTimerActivity() })
        val menuSettings = MenuItem(1, getString(R.string.settings), onClick = { openSettingsActivity() })

        menuTimer.secondaryLabel = getString(R.string.set_timer)
        menuTimer.icon = R.drawable.ic_clock

        menuSettings.secondaryLabel = getString(R.string.set_period)
        menuSettings.icon = R.drawable.ic_gear

        val menuHeader = MenuHeader(getString(R.string.app_name))
        val menuItems = listOf(menuTimer, menuSettings).toMutableList()

        setContent {
            AppTheme {
                MainMenu(menuHeader, menuItems)
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), PERMISSION_REQUEST_NOTIFICATION)
        } else {
            notificationWorkerUtils.enqueueNotificationWorker(timePreferencesService.getTime())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_NOTIFICATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notificationWorkerUtils.enqueueNotificationWorker(timePreferencesService.getTime())
                } else {
                    Toast.makeText(this, getString(R.string.notification_permission_denied), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openTimerActivity() {
        val intent = Intent(this, TimerActivity::class.java)
        startActivity(intent)
    }

    private fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val PERMISSION_REQUEST_NOTIFICATION = 1
    }
}
