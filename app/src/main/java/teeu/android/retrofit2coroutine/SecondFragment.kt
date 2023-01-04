package teeu.android.retrofit2coroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import teeu.android.retrofit2coroutine.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    val args : SecondFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentSecondBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_second, container, false)
        binding.lifecycleOwner = this

        binding.textView.setText(args.stringData)
        return binding.root
    }
}