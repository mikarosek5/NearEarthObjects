package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.DailyDao
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoCountDao
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoDao
import eu.invest.klk.neadearthobjects.data.db.daos.spacex.LaunchDao
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch
import eu.invest.klk.neadearthobjects.data.db.source_factory.NeoItemsDataSourceFactory
import eu.invest.klk.neadearthobjects.data.network.network_source.launch_library.LaunchLibraryNetworkSource
import eu.invest.klk.neadearthobjects.data.network.network_source.nasa.NasaNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

private const val PAGE_SIZE = 10

class NeoRepositoryImpl(
    private val dailyDao: DailyDao,
    private val neoCountDao: NeoCountDao,
    private val neoDao: NeoDao,
    private val launchDao: LaunchDao,
    private val launchLibraryNetworkSource: LaunchLibraryNetworkSource,
    private val nasaNetWorkDataSource: NasaNetWorkDataSource,
    private val dataSourceFactory: NeoItemsDataSourceFactory
) : NeoRepository {


    init {
        nasaNetWorkDataSource.downloadedDaily.observeForever { newDaily ->
            persistFetchedDaily(newDaily)
        }
        nasaNetWorkDataSource.downloadedNeoCount.observeForever { newNeoCount ->
            persistFetchedNeoCount(newNeoCount)
        }
        nasaNetWorkDataSource.downloadedNeoObjects.observeForever { newNeoObjects ->
            persistFetchedNeoObjects(newNeoObjects.nearEarthObjects)
        }
        launchLibraryNetworkSource.downloadedFalconLaunches.observeForever { spacexLaunch ->
            persistFetchedLaunchList(spacexLaunch.launches)
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
            initNeoObjects(page = page, size = size)
            return@withContext neoDao.getAllNeoObjects()
        }
    }

    override suspend fun getNeoObjectsListPaged(): LiveData<PagedList<NearEarthObject>> {
        return withContext(Dispatchers.IO) {
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 3)
                .build()
            return@withContext LivePagedListBuilder(
                dataSourceFactory,
                pagedListConfig
            ).build()
        }
    }

    override suspend fun getSpacexLaunches(): LiveData<List<Launch>> {
        return withContext(Dispatchers.IO) {
            initSpaceXLaunches()
            return@withContext launchDao.getAllLaunches()
        }
    }

    override fun invalidateNeoObjectsListPaged() {
        GlobalScope.launch(Dispatchers.IO) { dataSourceFactory.invalidateSource() }

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

    private fun persistFetchedNeoObjects(fetchedNeoObject: List<NearEarthObject>) {
        GlobalScope.launch(Dispatchers.IO) {
            neoDao.upsert(fetchedNeoObject)
        }
    }

    private fun persistFetchedLaunchList(fetchedLaunches: List<Launch>) {
        GlobalScope.launch(Dispatchers.IO) {
            launchDao.upsert(fetchedLaunches)
        }
    }

    private suspend fun initDailyData() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(4))) //Todo
            fetchDaily()
    }

    private suspend fun initNeoCountData() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(4)))
            fetchNeoCount()
    }

    private suspend fun initNeoObjects(page: Int, size: Int) {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(4)))
            fetchNeoObjects(page = page, size = size)
    }

    private suspend fun initSpaceXLaunches() {
        val oldLaunches = launchDao.getAllLaunches().value
        if (oldLaunches == null) {
            launchLibraryNetworkSource.fetchTenPendingFalcons()
            return
        }
        launchLibraryNetworkSource.fetchTenPendingFalcons()
    }


    private suspend fun fetchDaily() {
        nasaNetWorkDataSource.fetchDaily()

    }

    private suspend fun fetchNeoCount() {
        nasaNetWorkDataSource.fetchNeoCount()
    }

    private suspend fun fetchNeoObjects(page: Int, size: Int) {
        nasaNetWorkDataSource.fetchNeoObjects(page = page, size = size)
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoHoursAgo = ZonedDateTime.now().minusHours(2)
        return lastFetchTime.isBefore(twoHoursAgo)
    }

}