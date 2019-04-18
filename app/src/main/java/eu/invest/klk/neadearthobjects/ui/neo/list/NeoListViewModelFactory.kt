package eu.invest.klk.neadearthobjects.ui.neo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository

class NeoListViewModelFactory(private val neoRepository: NeoRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NeoListViewModel(neoRepository) as T
    }
}