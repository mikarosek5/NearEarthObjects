package eu.invest.klk.neadearthobjects.data.network.network_source.launch_library

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse
import eu.invest.klk.neadearthobjects.data.network.services.LaunchLibrary
import eu.invest.klk.neadearthobjects.internal.ConnectivityException

class LaunchLibraryNetworkSourceImpl(private val service:LaunchLibrary) : LaunchLibraryNetworkSource {

    private val _downloadedFalconLaunches = MutableLiveData<SpacexLaunchesResponse>()
    override val downloadedFalconLaunches: LiveData<SpacexLaunchesResponse>
        get() = _downloadedFalconLaunches

    override suspend fun fetchFivePendingFalcons() {
       try {
           val downloadedLaunches = service.downloadNextLaunchesAsync(5,"falcon").await()
           _downloadedFalconLaunches.postValue(downloadedLaunches)
       }catch (e:ConnectivityException){
           Log.e(this::class.java.simpleName,e.toString())
       }
    }
}
