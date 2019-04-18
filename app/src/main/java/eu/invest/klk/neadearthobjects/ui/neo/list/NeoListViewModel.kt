package eu.invest.klk.neadearthobjects.ui.neo.list

import androidx.lifecycle.ViewModel;
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred

class NeoListViewModel(private val repository: NeoRepository) : ViewModel() {
    val neoCount by lazyDeferred { repository.getNeoCount() }
}
