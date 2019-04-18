package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.NeoCount

interface NeoRepository {
    suspend fun getDailyInfo():LiveData<Daily>
    suspend fun getNeoCount():LiveData<NeoCount>
}