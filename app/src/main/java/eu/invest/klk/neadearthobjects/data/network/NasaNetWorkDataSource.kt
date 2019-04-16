package eu.invest.klk.neadearthobjects.data.network

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.db.Daily

interface NasaNetWorkDataSource {
    val downloadedDaily:LiveData<Daily>

    suspend fun fetchDaily()
}