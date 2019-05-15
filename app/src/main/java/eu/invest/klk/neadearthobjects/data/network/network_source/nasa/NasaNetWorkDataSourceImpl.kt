package eu.invest.klk.neadearthobjects.data.network.network_source.nasa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.network.response.NeoResponse
import eu.invest.klk.neadearthobjects.data.network.services.NasaService
import eu.invest.klk.neadearthobjects.internal.ConnectivityException
import eu.invest.klk.neadearthobjects.internal.Status

//Todo NETWORK TIMEOUT
class NasaNetWorkDataSourceImpl(private val nasaService: NasaService) :
    NasaNetWorkDataSource {

    private var _downloadingStatus = LiveEvent<Status>()

    override val downloadingStatus: LiveEvent<Status>
        get() = _downloadingStatus
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
            _downloadingStatus.postValue(Status.OK)
        } catch (e: ConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
            _downloadingStatus.postValue(Status.ERROR)
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
            val neoObjects =  nasaService.getNeoObjectsAsync(page, size).await().nearEarthObjects
            _downloadingStatus.postValue(Status.OK)
             neoObjects
        } catch (e: ConnectivityException) {
            _downloadingStatus.postValue(Status.ERROR)
             emptyList()
        }
    }


}