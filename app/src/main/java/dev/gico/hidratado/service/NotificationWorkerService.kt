package dev.gico.hidratado.service

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.gico.hidratado.data.preferences.TimePreferencesService
import dev.gico.hidratado.data.resources.MessagesService
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class NotificationWorkerService(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private lateinit var notificationService: NotificationService
    private lateinit var timePreferencesService: TimePreferencesService
    private lateinit var messagesService: MessagesService

    override fun doWork(): Result {
        try {
            notificationService = NotificationService(this.applicationContext)
            timePreferencesService = TimePreferencesService(this.applicationContext)
            messagesService = MessagesService(this.applicationContext)

            val localTime = LocalTime.now()
            val localTimeInitial = timePreferencesService.getSelectedInitialTime()
            val localTimeFinal = timePreferencesService.getSelectedFinalTime()

            if((localTime >= localTimeInitial) && (localTime <= localTimeFinal)) {
                notificationService.createNotification(messagesService.getMessage())
            }

            return Result.success()
        } catch (exception: Exception) {
            return Result.retry()
        }
    }

    companion object {
        fun createNotificationWorker(time: Int): PeriodicWorkRequest {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            return PeriodicWorkRequestBuilder<NotificationWorkerService>(
                repeatInterval = time.toLong(),
                repeatIntervalTimeUnit = TimeUnit.MINUTES,
                flexTimeInterval = 5,
                flexTimeIntervalUnit = TimeUnit.MINUTES
            ).setConstraints(constraints).build()
        }
    }
}
