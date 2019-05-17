package eu.invest.klk.neadearthobjects.ui.picture_of_day

import androidx.lifecycle.ViewModel
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred

class PictureOfDayViewModel(private val neoRepository: NeoRepository) : ViewModel() {
    val daily by lazyDeferred { neoRepository.getDailyInfo() }
    val status by lazyDeferred { neoRepository.getDownloadingStatus() }
    suspend fun refresh() = neoRepository.refreshDaily()
}