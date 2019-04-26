package eu.invest.klk.neadearthobjects.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.try_modular.neoResponse.NeoResponse
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.internal.ConnectivityException

class NasaNetWorkDataSourceImpl(private val nasaService: NasaService) : NasaNetWorkDataSource {
    private val _downloadedDaily = MutableLiveData<Daily>()
    override val downloadedDaily: LiveData<Daily>
        get() = _downloadedDaily

    private val _downloadedNeoCount = MutableLiveData<NeoCount>()
    override val downloadedNeoCount: LiveData<NeoCount>
        get() = _downloadedNeoCount

    private val _downloadedNeoObjects = MutableLiveData<NeoResponse>()

    override val downloadedNeoObjects: LiveData<NeoResponse>
        get() = _downloadedNeoObjects

    override suspend fun fetchDaily() {
        try {
            val fetchedDaily = nasaService.getToadyInfoAsync().await()
            _downloadedDaily.postValue(fetchedDaily)
        } catch (e: ConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }


    }

    override suspend fun fetchNeoCount() {
        try {
            val fetchedNeoCount = nasaService.neoCountAsync().await()
            _downloadedNeoCount.postValue(fetchedNeoCount)
        } catch (e: ConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchNeoObjects(page: Int, size: Int) {
        try {
            val fetchedNeoResponse = nasaService.getNeoObjectsAsync(page, size).await()
            _downloadedNeoObjects.postValue(fetchedNeoResponse)
        } catch (e: ConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchNeoObjectForDataSource(page: Int, size: Int): List<NearEarthObject> {
        return try {
            nasaService.getNeoObjectsAsync(page, size).await().nearEarthObjects
        } catch (e: ConnectivityException) {
            emptyList()
        }
    }
}