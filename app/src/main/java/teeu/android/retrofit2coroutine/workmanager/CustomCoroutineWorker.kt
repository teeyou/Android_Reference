package teeu.android.retrofit2coroutine.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import teeu.android.retrofit2coroutine.MainActivity

const val NOTIFICATION_ID = 1111

// 12이상에서는 ForegroundServiceStartNotAllowedException 사용가능
// 12이전 버전에서 아래와 같이 사용가능
class CustomCoroutineWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    private fun createNotification() : Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat
            .Builder(context, "foreground_channel_id")
            .setTicker("ticker")
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            //.setLargeIcon()
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentTitle("title")
            .setContentText("text")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        return notification
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            NOTIFICATION_ID, createNotification()
        )
    }
    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
//            setForeground(getForegroundInfo())
            Log.d("MYTAG","coroutineWorker")
        }
        Result.success()
    }

}