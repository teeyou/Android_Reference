package teeu.android.retrofit2coroutine

import android.app.LauncherActivity.ListItem
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import teeu.android.retrofit2coroutine.databinding.FragmentRetrofitBinding
import teeu.android.retrofit2coroutine.databinding.FragmentSecondBinding
import teeu.android.retrofit2coroutine.databinding.ListItemBinding
import teeu.android.retrofit2coroutine.retrofit.RetrofitClient

class RetrofitFragment : Fragment() {
    val viewModel: RetrofitViewModel by viewModels()
    lateinit var binding: FragmentRetrofitBinding

//    companion object {
//        @JvmStatic
//        @BindingAdapter("itemImage")
//        fun loadImage(view: ImageView, image_url: String) {
//            Glide.with(view.context)
//                .load(image_url)
//                .into(view)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_retrofit, container, false)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            adapter = CustomAdapter(emptyList())
        }
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.itemsLiveData.observe(viewLifecycleOwner, { items ->
            binding.recyclerView.adapter = CustomAdapter(items)
        })
    }

    private inner class CustomAdapter(private val list: List<Item>) :
        RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val binding = DataBindingUtil.inflate<ListItemBinding>(
                layoutInflater,
                R.layout.list_item,
                parent,
                false
            )
            binding.lifecycleOwner = this@RetrofitFragment
            return CustomViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val item = list[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = list.size

    }

    private inner class CustomViewHolder(val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item

//            binding.textViewId.setText(item.id.toString())
//            binding.textViewTitle.setText(item.title)
            Glide.with(binding.root.context)
                .load(item.img_url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageViewImg)
        }
    }
}