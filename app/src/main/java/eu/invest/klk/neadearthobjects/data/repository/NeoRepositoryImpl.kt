package eu.invest.klk.neadearthobjects.data.repository

import androidx.lifecycle.LiveData
import eu.invest.klk.neadearthobjects.data.db.entity.Daily
import eu.invest.klk.neadearthobjects.data.db.DailyDao
import eu.invest.klk.neadearthobjects.data.network.NasaNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class NeoRepositoryImpl(
    private val dailyDao: DailyDao,
    private val nasaNetWorkDataSource: NasaNetWorkDataSource
) : NeoRepository {
    init {
        nasaNetWorkDataSource.downloadedDaily.observeForever { newDaily ->
            persistFetchedDaily(newDaily)
        }
    }

    override suspend fun getDailyInfo(): LiveData<Daily> {
        return withContext(Dispatchers.IO) {
            initDailyData()
            return@withContext dailyDao.getDaily()
        }
    }

    private fun persistFetchedDaily(fetchedDaily: Daily) {
        GlobalScope.launch(Dispatchers.IO) {
            dailyDao.upsert(fetchedDaily)
        }
    }

    private suspend fun initDailyData() {
        if (isFetchDailyNeded(ZonedDateTime.now().minusHours(4))) //Todo
            fetchDaily()
    }

    private suspend fun fetchDaily() {
        nasaNetWorkDataSource.fetchDaily()
    }

    private fun isFetchDailyNeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoHoursAgo = ZonedDateTime.now().minusHours(2)
        return lastFetchTime.isBefore(twoHoursAgo)
    }

}