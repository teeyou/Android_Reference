package teeu.android.retrofit2coroutine

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import teeu.android.retrofit2coroutine.retrofit.RetrofitClient

class RetrofitViewModel : ViewModel() {
    val itemsLiveData = MutableLiveData<List<Item>>()

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = async {
                RetrofitClient().getItemApi().getItems()
            }
            itemsLiveData.postValue(items.await())
        }
    }
}