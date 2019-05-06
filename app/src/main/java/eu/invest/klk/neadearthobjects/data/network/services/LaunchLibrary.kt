package eu.invest.klk.neadearthobjects.data.network.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import eu.invest.klk.neadearthobjects.data.network.interceptors.ConnectivityInterceptor
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.math.log

interface LaunchLibrary {

    @GET("launch")
    fun downloadNextLaunchesAsync(@Query("next") next: Int, @Query("name") name: String): Deferred<SpacexLaunchesResponse>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor):LaunchLibrary {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://launchlibrary.net/1.3/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LaunchLibrary::class.java)
        }
    }
}