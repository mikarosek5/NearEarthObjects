package eu.invest.klk.neadearthobjects.ui.spacex.next_launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository

class SpaceXdetailsViewModelFactory(private val repository: NeoRepository):ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpaceXdetailsViewModel(repository) as T
    }
}