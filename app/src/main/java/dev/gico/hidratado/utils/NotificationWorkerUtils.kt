package dev.gico.hidratado.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import dev.gico.hidratado.service.NotificationWorkerService

class NotificationWorkerUtils(private val context: Context) {

    fun enqueueNotificationWorker(time: Int) {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "hidratado-notification-worker",
            ExistingPeriodicWorkPolicy.UPDATE,
            NotificationWorkerService.createNotificationWorker(time)
        )
    }
}
