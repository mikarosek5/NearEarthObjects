package eu.invest.klk.neadearthobjects.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.internal.ConnectivityException

class NasaNetWorkDataSourceImpl(private val nasaService:NasaService) : NasaNetWorkDataSource {
    private val  _downloadedDaily = MutableLiveData<Daily>()
    override val downloadedDaily: LiveData<Daily>
        get() = _downloadedDaily

    override suspend fun fetchDaily() {
        try {
            val fetchedDaily = nasaService.getToadyInfo().await()
            _downloadedDaily.postValue(fetchedDaily)
        }catch (e: ConnectivityException){
            Log.e("Connectivity","No internet connection.", e)
        }

    }
}