package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import com.example.try_modular.neoResponse.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount

interface NeoRepository {
    suspend fun getDailyInfo():LiveData<Daily>
    suspend fun getNeoCount():LiveData<NeoCount>
    suspend fun getNeoObjectsList(page:Int,size:Int):LiveData<NearEarthObject>
}