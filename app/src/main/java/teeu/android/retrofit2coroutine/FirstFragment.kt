package teeu.android.retrofit2coroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import teeu.android.retrofit2coroutine.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    lateinit var binding : FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
//            val action = R.id.action_first_fragment_to_second_fragment //그냥 이동
//            val data : String = "data"
//            val bundle = bundleOf("data" to data)
//            findNavController().navigate(action,bundle)

            val args = "safe args"
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(args) //데이터 전달하면서 이동
            findNavController().navigate(action)

        }
    }
}