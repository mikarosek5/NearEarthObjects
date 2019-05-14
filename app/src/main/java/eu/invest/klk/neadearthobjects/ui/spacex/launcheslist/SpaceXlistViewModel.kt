package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist

import androidx.lifecycle.ViewModel
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred

class SpaceXlistViewModel(private val repository: NeoRepository) : ViewModel() {

    val launches by lazyDeferred { repository.getSpacexLaunches() }
    val status by lazyDeferred { repository.getDownloadingStatusSpaxeX() }



}
