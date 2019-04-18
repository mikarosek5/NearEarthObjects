package eu.invest.klk.neadearthobjects.data.network

import androidx.lifecycle.LiveData
import com.example.try_modular.neoResponse.NeoResponse
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount

interface NasaNetWorkDataSource {
    val downloadedDaily:LiveData<Daily>
    val downloadedNeoCount:LiveData<NeoCount>
    val downloadedNeoObjects:LiveData<NeoResponse>

    suspend fun fetchDaily()
    suspend fun fetchNeoCount()
    suspend fun fetchNeoObjects(page:Int,size:Int)
}