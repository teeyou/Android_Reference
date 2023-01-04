package teeu.android.retrofit2coroutine

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

const val NOTIFICATION_CHANNEL_ID = "teeu.android.retrofit2coroutine.channel"

class CustomApplication : Application() {
    fun createNotiChannel() {
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID,"channel_name",NotificationManager.IMPORTANCE_HIGH)
        channel.description = "description"

        //val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onCreate() {
        super.onCreate()
        //Activity 실행 전에 실행됨
        //Manifests 에서 name을 클래스이름으로 설정해줘야함

        //오레오 이상부터는 notification 쓰려면 채널을 만들어놔야함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotiChannel()
        }
    }

}