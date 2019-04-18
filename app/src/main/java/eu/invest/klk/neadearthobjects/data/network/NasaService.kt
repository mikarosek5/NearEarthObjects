package eu.invest.klk.neadearthobjects.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.NeoCount
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//const val api_key = "DEMO_KEY"
const val api_key = "NNKOjkoul8n1CH18TWA9gwngW1s1SmjESPjNoUFo"

//NNKOjkoul8n1CH18TWA9gwngW1s1SmjESPjNoUFo
interface NasaService {

    @GET("planetary/apod")
    fun getToadyInfoAsync(): Deferred<Daily>

    @GET("neo/rest/v1/stats")
    fun neoCountAsync():Deferred<NeoCount>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): NasaService {
            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", api_key)
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor it.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.nasa.gov/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NasaService::class.java)
        }
    }
}