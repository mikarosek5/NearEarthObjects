package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.list.Launch
import eu.invest.klk.neadearthobjects.internal.Status

interface NeoRepository {
    suspend fun getDailyInfo():LiveData<Daily>
    suspend fun getNeoCount():LiveData<NeoCount>
    suspend fun getNeoObjectsList(page:Int,size:Int):LiveData<List<NearEarthObject>>
    suspend fun getNeoObjectsListPaged():LiveData<PagedList<NearEarthObject>>
    suspend fun getSpacexLaunches():LiveData<List<Launch>>
    suspend fun getDownloadingStatus():LiveEvent<Status>
    suspend fun getDownloadingStatusSpaxeX():LiveEvent<Status>
    suspend fun refreshDaily()
    suspend fun refreshLaunches()
    fun invalidateNeoObjectsListPaged()
}