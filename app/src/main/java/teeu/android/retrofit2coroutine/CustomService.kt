package teeu.android.retrofit2coroutine

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.session.PlaybackState.ACTION_STOP
import android.os.IBinder

class CustomService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            "stop" -> {
                stopForeground(true)
                stopSelf()
            }
            "start" -> start()
        }
        return START_NOT_STICKY
    }

    private fun start() {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE)
            }

        val notification: Notification = Notification.Builder(this, "foreground_channel_id")
            .setContentTitle("title")
            .setContentText("text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setTicker("ticker")
            .build()

        // Notification ID cannot be 0.
        startForeground(1,notification)
    }
}