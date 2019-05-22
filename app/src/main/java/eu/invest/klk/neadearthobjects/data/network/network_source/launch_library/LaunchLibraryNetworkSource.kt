package eu.invest.klk.neadearthobjects.data.network.network_source.launch_library

import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details.Launche
import eu.invest.klk.neadearthobjects.data.network.response.SpacexLaunchesResponse
import eu.invest.klk.neadearthobjects.internal.Status

interface LaunchLibraryNetworkSource {
    suspend fun fetchTenPendingFalcons()
    suspend fun fetchLaunchDetalis(id:Int)
    val downloadedFalconLaunches:LiveData<SpacexLaunchesResponse>
    val downloadSpaceXStatus:LiveEvent<Status>
    val downloadedDetails:LiveData<Launche>

}