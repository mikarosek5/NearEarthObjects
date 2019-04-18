package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
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
        nasaNetWorkDataSource.downloadedNeoObjects.observeForever {  } //Todo(!!!!!!!!!!!!!!!!!!!!!!!!)
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

    private suspend fun initDailyData() {
        if (isFetchNeded(ZonedDateTime.now().minusHours(4))) //Todo
            fetchDaily()
    }

    private suspend fun initNeoCountData() {
        if (isFetchNeded(ZonedDateTime.now().minusHours(4)))
            fetchNeoCount()
    }


    private suspend fun fetchDaily() {
        nasaNetWorkDataSource.fetchDaily()

    }

    private suspend fun fetchNeoCount() {
        nasaNetWorkDataSource.fetchNeoCount()
    }

    private fun isFetchNeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoHoursAgo = ZonedDateTime.now().minusHours(2)
        return lastFetchTime.isBefore(twoHoursAgo)
    }

}