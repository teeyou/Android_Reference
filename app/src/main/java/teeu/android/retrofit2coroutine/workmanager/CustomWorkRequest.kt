package teeu.android.retrofit2coroutine.workmanager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class CustomWorkRequest {
    fun oneTimeWork(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED).build() //비용이 들지않는 네트워크

        // Android 12 (S) 이전 버전에서는 Workmanager + 포그라운드 사용 가능
        val workRequest =
            OneTimeWorkRequestBuilder<CustomWorker>()
//                OneTimeWorkRequest.Builder(CustomWorker::class.java)
//                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) //즉시 백그라운드에서 실행
                .setInitialDelay(10, TimeUnit.SECONDS) //지연 가능
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }

    fun periodicWork(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build() //네트워크 연결되어 있어야함

        val periodicRequest = PeriodicWorkRequest
            .Builder(CustomWorker::class.java, 15, TimeUnit.MINUTES) //최소 15분 간격
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "workName",
            ExistingPeriodicWorkPolicy.KEEP, //동일한 이름의 새로운 요청이 들어와도 무시하고 진행
            periodicRequest
        )
    }

    fun coroutineWork(context: Context) {
        val workRequest =
            OneTimeWorkRequestBuilder<CustomCoroutineWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS) //지연 가능
                .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}