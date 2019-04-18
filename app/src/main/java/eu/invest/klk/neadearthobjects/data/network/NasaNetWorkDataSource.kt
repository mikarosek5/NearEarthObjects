package eu.invest.klk.neadearthobjects.data.network

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.NeoCount

interface NasaNetWorkDataSource {
    val downloadedDaily:LiveData<Daily>
    val downloadedNeoCount:LiveData<NeoCount>

    suspend fun fetchDaily()
    suspend fun fetchNeoCount()
}