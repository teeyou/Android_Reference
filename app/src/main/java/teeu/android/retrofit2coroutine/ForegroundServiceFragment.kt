package teeu.android.retrofit2coroutine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import teeu.android.retrofit2coroutine.databinding.FragmentForegroundBinding
import teeu.android.retrofit2coroutine.workmanager.CustomWorkRequest

class ForegroundServiceFragment : Fragment() {
    lateinit var binding : FragmentForegroundBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_foreground,container,false)
        binding.lifecycleOwner = this
        binding.buttonStart.setOnClickListener {
            val intent = Intent(context,CustomService::class.java)
            intent.action = "start"
            requireActivity().startForegroundService(intent) // 오레오 이상부터 이렇게 실행
            CustomWorkRequest().coroutineWork(requireActivity())
        }
        binding.buttonStop.setOnClickListener {
            val intent = Intent(context,CustomService::class.java)
            intent.action = "stop"
            requireActivity().startForegroundService(intent)
        }
        return binding.root
    }
}