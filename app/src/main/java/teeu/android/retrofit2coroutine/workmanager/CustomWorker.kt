package teeu.android.retrofit2coroutine.workmanager

import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log

import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import teeu.android.retrofit2coroutine.MainActivity


class CustomWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("MYTAG", "Test Worker")
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(2000,VibrationEffect.DEFAULT_AMPLITUDE))
        return Result.success()
    }
}