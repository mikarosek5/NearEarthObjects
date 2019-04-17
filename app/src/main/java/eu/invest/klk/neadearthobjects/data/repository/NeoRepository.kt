package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily

interface NeoRepository {
    suspend fun getDailyInfo():LiveData<Daily>
}