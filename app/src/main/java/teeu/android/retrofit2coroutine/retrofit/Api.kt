package teeu.android.retrofit2coroutine.retrofit

import retrofit2.http.GET
import teeu.android.retrofit2coroutine.Item

const val BASE_URL = "https://iamtaeung.github.io/"
interface Api {
    @GET("/json_data/")
    suspend fun getItems(): List<Item>
}