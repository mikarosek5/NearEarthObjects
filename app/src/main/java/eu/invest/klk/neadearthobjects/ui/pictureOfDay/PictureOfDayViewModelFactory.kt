package eu.invest.klk.neadearthobjects.ui.pictureOfDay

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository

class PictureOfDayViewModelFactory(private val neoRepository: NeoRepository):ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PictureOfDayViewModel(neoRepository) as T
    }
}