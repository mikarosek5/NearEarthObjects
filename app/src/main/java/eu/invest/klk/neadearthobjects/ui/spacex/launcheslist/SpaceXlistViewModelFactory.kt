package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository

class SpaceXlistViewModelFactory(private val repository: NeoRepository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpaceXlistViewModel(repository) as T
    }
}