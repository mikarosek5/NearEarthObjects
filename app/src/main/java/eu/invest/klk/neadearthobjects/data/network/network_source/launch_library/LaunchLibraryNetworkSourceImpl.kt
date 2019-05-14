package eu.invest.klk.neadearthobjects.data.network.network_source.launch_library

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse
import eu.invest.klk.neadearthobjects.data.network.services.LaunchLibrary
import eu.invest.klk.neadearthobjects.internal.ConnectivityException
import eu.invest.klk.neadearthobjects.internal.Status

//Todo NETWORK TIMEOUT
class LaunchLibraryNetworkSourceImpl(private val service:LaunchLibrary) : LaunchLibraryNetworkSource {

    private var _downloadSpaceXStatus = LiveEvent<Status>()
    private val _downloadedFalconLaunches = MutableLiveData<SpacexLaunchesResponse>()
    override val downloadedFalconLaunches: LiveData<SpacexLaunchesResponse>
        get() = _downloadedFalconLaunches

    override val downloadSpaceXStatus: LiveEvent<Status>
        get() = _downloadSpaceXStatus

    override suspend fun fetchTenPendingFalcons() {
       try {
           val downloadedLaunches = service.downloadNextLaunchesAsync(10,"falcon").await()
           _downloadedFalconLaunches.postValue(downloadedLaunches)
           _downloadSpaceXStatus.postValue(Status.OK)
       }catch (e:ConnectivityException){
           Log.e(this::class.java.simpleName,e.toString())
           _downloadSpaceXStatus.postValue(Status.ERROR)
       }
    }


}
