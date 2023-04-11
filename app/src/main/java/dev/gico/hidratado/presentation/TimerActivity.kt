package dev.gico.hidratado.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.gico.hidratado.R
import dev.gico.hidratado.data.MenuHeader
import dev.gico.hidratado.data.MenuItemRadio
import dev.gico.hidratado.presentation.theme.AppTheme
import dev.gico.hidratado.presentation.ui.time.TimerOptions
import dev.gico.hidratado.service.TimePreferencesService
import dev.gico.hidratado.utils.NotificationWorkerUtils

class TimerActivity : ComponentActivity() {

    private lateinit var timePreferencesService: TimePreferencesService
    private lateinit var notificationWorkerUtils: NotificationWorkerUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timePreferencesService = TimePreferencesService(this)
        notificationWorkerUtils = NotificationWorkerUtils(this)

        val menuHeader = MenuHeader(getString(R.string.timer))
        val menuItems = createMenuItems(listOf(15, 30, 45, 60))

        setContent {
            AppTheme {
                TimerOptions(menuHeader, menuItems)
            }
        }
    }

    private fun createMenuItems(items: List<Int>): MutableList<MenuItemRadio> {
        val time = timePreferencesService.getTime()
        val menuItems = mutableListOf<MenuItemRadio>()

        items.forEach { item ->
            menuItems.add(MenuItemRadio(item, "$item ${getString(R.string.minutes)}", time == item, onClick = { saveSelectedTime(item) }))
        }

        return menuItems
    }

    private fun saveSelectedTime(time: Int) {
        timePreferencesService.saveTime(time)
        notificationWorkerUtils.enqueueNotificationWorker(timePreferencesService.getTime())
    }
}
