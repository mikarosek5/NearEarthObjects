package eu.invest.klk.neadearthobjects.ui.pictureOfDay

import androidx.lifecycle.ViewModel
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred

class PictureOfDayViewModel(private val neoRepository: NeoRepository) : ViewModel() {
    val daily by lazyDeferred { neoRepository.getDailyInfo() }
    val status by lazyDeferred { neoRepository.getDownloadingStatus() }
}
