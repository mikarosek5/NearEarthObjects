package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.DailyDao
import eu.invest.klk.neadearthobjects.data.db.NeoCountDao
import eu.invest.klk.neadearthobjects.data.db.NeoDao
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.network.NasaNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class NeoRepositoryImpl(
    private val dailyDao: DailyDao,
    private val neoCountDao: NeoCountDao,
    private val neoDao: NeoDao,
    private val nasaNetWorkDataSource: NasaNetWorkDataSource
) : NeoRepository {
    init {
        nasaNetWorkDataSource.downloadedDaily.observeForever { newDaily ->
            persistFetchedDaily(newDaily)
        }
        nasaNetWorkDataSource.downloadedNeoCount.observeForever { newNeoCount ->
            persistFetchedNeoCount(newNeoCount)
        }
        nasaNetWorkDataSource.downloadedNeoObjects.observeForever {newNeoObjects ->
            persistFetchedNeoObjects(newNeoObjects.nearEarthObjects)
        }
    }

    override suspend fun getDailyInfo(): LiveData<Daily> {
        return withContext(Dispatchers.IO) {
            initDailyData()
            return@withContext dailyDao.getDaily()
        }
    }

    override suspend fun getNeoCount(): LiveData<NeoCount> {
        return withContext(Dispatchers.IO) {
            initNeoCountData()
            return@withContext neoCountDao.getNeo()
        }
    }

    override suspend fun getNeoObjectsList(page: Int, size: Int): LiveData<List<NearEarthObject>> {
        return withContext(Dispatchers.IO) {
            initNeoObjects(page = page,size = size)
            return@withContext neoDao.getAllNeoObjects()
        }
    }

    override suspend fun getNeoObjectsListPaged(page: Int, size: Int): LiveData<PagedList<NearEarthObject>> {
        return withContext(Dispatchers.IO) {
            initNeoObjects(page = page,size = size)
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .setInitialLoadSizeHint(30)
                .build()
            return@withContext LivePagedListBuilder<Int,NearEarthObject>(neoDao.getAllNeoObjectsPaged(),pagedListConfig).build()
        }
    }

    private fun persistFetchedDaily(fetchedDaily: Daily) {
        GlobalScope.launch(Dispatchers.IO) {
            dailyDao.upsert(fetchedDaily)
        }
    }

    private fun persistFetchedNeoCount(fetchedNeoCount: NeoCount) {
        GlobalScope.launch(Dispatchers.IO) {
            neoCountDao.upsert(fetchedNeoCount)
        }
    }
    private fun persistFetchedNeoObjects(fetchedNeoObject: List<NearEarthObject>){
        GlobalScope.launch(Dispatchers.IO) {
            neoDao.upsert(fetchedNeoObject)
        }
    }

    private suspend fun initDailyData() {
        if (isFetchNeded(ZonedDateTime.now().minusHours(4))) //Todo
            fetchDaily()
    }

    private suspend fun initNeoCountData() {
        if (isFetchNeded(ZonedDateTime.now().minusHours(4)))
            fetchNeoCount()
    }
    private suspend fun initNeoObjects(page:Int,size:Int){
        if (isFetchNeded(ZonedDateTime.now().minusHours(4)))
            fetchNeoObjects(page = page,size = size)
    }


    private suspend fun fetchDaily() {
        nasaNetWorkDataSource.fetchDaily()

    }

    private suspend fun fetchNeoCount() {
        nasaNetWorkDataSource.fetchNeoCount()
    }

    private suspend fun fetchNeoObjects(page:Int,size:Int){
        nasaNetWorkDataSource.fetchNeoObjects(page = page,size = size)
    }

    private fun isFetchNeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoHoursAgo = ZonedDateTime.now().minusHours(2)
        return lastFetchTime.isBefore(twoHoursAgo)
    }

}