package eu.invest.klk.neadearthobjects.data.network.network_source.launch_library

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse

interface LaunchLibraryNetworkSource {
    suspend fun fetchTenPendingFalcons()
    val downloadedFalconLaunches:LiveData<SpacexLaunchesResponse>

}