package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch

interface NeoRepository {
    suspend fun getDailyInfo():LiveData<Daily>
    suspend fun getNeoCount():LiveData<NeoCount>
    suspend fun getNeoObjectsList(page:Int,size:Int):LiveData<List<NearEarthObject>>
    suspend fun getNeoObjectsListPaged():LiveData<PagedList<NearEarthObject>>
    suspend fun getSpacexLaunches():LiveData<List<Launch>>
    fun invalidateNeoObjectsListPaged()
}