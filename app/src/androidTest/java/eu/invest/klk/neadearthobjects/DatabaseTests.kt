package eu.invest.klk.neadearthobjects

import android.util.Log
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import eu.invest.klk.neadearthobjects.data.db.NeoDatabase
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.DailyDao
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoCountDao
import eu.invest.klk.neadearthobjects.data.db.daos.spacex.LaunchDao
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTests {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getTargetContext()
//        assertEquals("eu.invest.klk.neadearthobjects", appContext.packageName)
//    }

    private lateinit var dailyDao: DailyDao
    private lateinit var neoCountDao: NeoCountDao
    private lateinit var launchDao: LaunchDao
    private lateinit var db:NeoDatabase
    @Before
    fun createDb(){
        val appContext = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(appContext,NeoDatabase::class.java).build()
        dailyDao = db.dailyDao()
        neoCountDao = db.neoCountDao()
        launchDao = db.launchDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadDaily(){
        val daily = Daily("a","a","a")
        dailyDao.upsert(daily)
        val queryDaily = dailyDao.getDaily()
        var dailyDb:Daily? = null
        runBlocking(Dispatchers.Main) {
            queryDaily.observeForever {
                if (it==null)
                    return@observeForever
                dailyDb = it
            }
        }
        Thread.sleep(1000)
        Log.d(daily.toString(),dailyDb.toString())
        assertEquals(daily,dailyDb)
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadNeoCount(){
        val neoCount = NeoCount((Math.random()*100).toInt(),100)
        neoCountDao.upsert(neoCount)
        val queryCountDao = neoCountDao.getNeo()
        var neoCountDb:NeoCount? = null
        runBlocking(Dispatchers.Main) {
            queryCountDao.observeForever {
                if (it==null)
                    return@observeForever
                neoCountDb = it
            }
        }
        Thread.sleep(1000)
        assertEquals(neoCount,neoCountDb)
    }


    @Test
    @Throws(Exception::class)
    fun writeLaunchesAndReadList(){
        val launches = ArrayList<Launch>()
        launches.add(Launch(1,"aaa","aaa"))
        launches.add(Launch(2,"bbb","bbb"))
        launches.add(Launch(3,"ccc","ccc"))
        launches.add(Launch(4,"ddd","ddd"))

        launchDao.upsert(launches)

        var lauchDb= ArrayList<Launch>()

        val queryLaunch = launchDao.getAllLaunches()

        runBlocking(Dispatchers.Main) {
            queryLaunch.observeForever {
                if(it==null)
                    return@observeForever
                lauchDb.addAll(it)
            }
        }
        Thread.sleep(2000)
        assertEquals(lauchDb,launches)
    }
}
