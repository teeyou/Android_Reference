package teeu.android.retrofit2coroutine

import android.app.Notification
import android.app.Notification.Action
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkRequest
import kotlinx.coroutines.launch
import teeu.android.retrofit2coroutine.workmanager.CustomCoroutineWorker
import teeu.android.retrofit2coroutine.workmanager.CustomWorkRequest
import teeu.android.retrofit2coroutine.workmanager.CustomWorker


class MainActivity : AppCompatActivity() {
    fun showNoti() {
        //안드로이드 8.0 이상부터 channel 생성해서 notify해야함, Application에 생성해둠
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat
            .Builder(this, NOTIFICATION_CHANNEL_ID)
            .setTicker(resources.getString(R.string.noti_ticker))
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            //.setLargeIcon()
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentTitle(resources.getString(R.string.noti_title))
            .setContentText(resources.getString(R.string.noti_text))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(makeNotiAction("action1"))
            .addAction(makeNotiAction("action2"))
            .addAction(makeNotiAction("action3"))
            .build()
        NotificationManagerCompat.from(this).notify(0,notification)
    }

    fun makeNotiAction(title : String) : NotificationCompat.Action {
        val actionIntent = Intent(this,MainActivity::class.java)
        val actionPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val action = NotificationCompat.Action.Builder(0,title,actionPendingIntent)
            //.addRemoteInput() - 이걸 수행하면 noti에서 텍스트 입력가능
            .build()
        return action
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefData : String = getSharedPreferences("myPref",Context.MODE_PRIVATE).getString("key","No Data") ?: "null"
        Toast.makeText(this,prefData,Toast.LENGTH_SHORT).show()

//        CustomWorkRequest().oneTimeWork(this)
//        CustomWorkRequest().periodicWork(this)
//        WorkManager.getInstance(this).cancelUniqueWork("workName")
//        CustomWorkRequest().coroutineWork(this)
    }

    override fun onStop() {
        super.onStop()
        showNoti()
    }

    override fun onDestroy() {
        super.onDestroy()
        val pref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        pref.edit()
            .putString("key","value")
            .apply()
    }


    fun vibrate() {
        //        26 ~ 30 까지는 아래처럼 사용
//        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        val effect = VibrationEffect.createOneShot(
//            2000, VibrationEffect.DEFAULT_AMPLITUDE)
//        vibrator.vibrate(effect)


//        31부터
//        val vibratorManager: VibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrationEffect = VibrationEffect.createOneShot(
//            500L,
//            VibrationEffect.DEFAULT_AMPLITUDE
//        )
//        val combinedVibration = CombinedVibration.createParallel(vibrationEffect)
//        vibratorManager.vibrate(combinedVibration)
    }
}