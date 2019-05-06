package eu.invest.klk.neadearthobjects.data.network.network_source.nasa

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.network.response.NeoResponse
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject

interface NasaNetWorkDataSource {
    val downloadedDaily:LiveData<Daily>
    val downloadedNeoCount:LiveData<NeoCount>
    val downloadedNeoObjects:LiveData<NeoResponse>

    suspend fun fetchDaily()
    suspend fun fetchNeoCount()
    suspend fun fetchNeoObjects(page:Int,size:Int)
    suspend fun fetchNeoObjectForDataSource(page:Int,size:Int):List<NearEarthObject>
}