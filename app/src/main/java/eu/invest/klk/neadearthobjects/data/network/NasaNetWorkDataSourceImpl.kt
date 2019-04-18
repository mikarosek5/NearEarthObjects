package eu.invest.klk.neadearthobjects.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.NeoCount
import eu.invest.klk.neadearthobjects.internal.ConnectivityException

class NasaNetWorkDataSourceImpl(private val nasaService:NasaService) : NasaNetWorkDataSource {
    private val  _downloadedDaily = MutableLiveData<Daily>()
    override val downloadedDaily: LiveData<Daily>
        get() = _downloadedDaily

    private val  _downloadedNeoCount = MutableLiveData<NeoCount>()
    override val downloadedNeoCount: LiveData<NeoCount>
        get() = _downloadedNeoCount

    override suspend fun fetchDaily() {
        try {
            val fetchedDaily = nasaService.getToadyInfoAsync().await()
            _downloadedDaily.postValue(fetchedDaily)
        }catch (e: ConnectivityException){
            Log.e("Connectivity","No internet connection.", e)
        }


    }

    override suspend fun fetchNeoCount() {
        try {
            val fetchedNeoCount = nasaService.neoCountAsync().await()
            _downloadedNeoCount.postValue(fetchedNeoCount)
        }catch (e:ConnectivityException){
            Log.e("Connectivity","No internet connection.", e)
        }
    }
}