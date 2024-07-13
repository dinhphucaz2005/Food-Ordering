import androidx.compose.ui.util.packInts
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.util.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val retrofit: Retrofit = createRetrofit()
    private lateinit var retrofitWithToken: Retrofit

    private val apiService: ApiService = retrofit.create(ApiService::class.java)
    private lateinit var apiServiceWithToken: ApiService

    fun getApiService(): ApiService = apiService

    fun getApiServiceWithToken(): ApiService = apiServiceWithToken

    private fun createRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS).build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()
    }

    fun createRetrofitWithToken(token: String) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithToken =
                    originalRequest.newBuilder().header("Authorization", "Bearer $token").build()
                chain.proceed(requestWithToken)
            }.build()

        val gson = GsonBuilder().setLenient().create()

        retrofitWithToken = Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()
        apiServiceWithToken = retrofitWithToken.create(ApiService::class.java)
    }
}
