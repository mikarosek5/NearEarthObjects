package eu.invest.klk.neadearthobjects.ui.spacex.next_launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details.Launche
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred
import kotlinx.coroutines.Deferred

class SpaceXdetailsViewModel(private val repository: NeoRepository) : ViewModel() {
    suspend fun getDetails(id:Int):Lazy<Deferred<LiveData<Launche>>>{
        return lazyDeferred { repository.getLaunchDetails(id) }
    }
    val status by lazyDeferred { repository.getDownloadingStatusSpaxeX() }
}
