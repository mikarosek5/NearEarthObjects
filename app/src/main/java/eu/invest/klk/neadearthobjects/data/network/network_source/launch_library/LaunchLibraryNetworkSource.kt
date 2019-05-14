package eu.invest.klk.neadearthobjects.data.network.network_source.launch_library

import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse
import eu.invest.klk.neadearthobjects.internal.Status

interface LaunchLibraryNetworkSource {
    suspend fun fetchTenPendingFalcons()
    val downloadedFalconLaunches:LiveData<SpacexLaunchesResponse>
    val downloadSpaceXStatus:LiveEvent<Status>

}