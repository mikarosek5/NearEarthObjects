package eu.invest.klk.neadearthobjects.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.DailyDao
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoCountDao
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoDao
import eu.invest.klk.neadearthobjects.data.db.daos.spacex.LaunchDao
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch


@Database(
    entities = [Daily::class, NeoCount::class, NearEarthObject::class, Launch::class],
    version = 2,
    exportSchema = false
)
abstract class NeoDatabase : RoomDatabase() {
    abstract fun dailyDao(): DailyDao
    abstract fun neoCountDao(): NeoCountDao
    abstract fun neoDao(): NeoDao
    abstract fun launchDao(): LaunchDao

    companion object {
        @Volatile
        var instance: NeoDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NeoDatabase::class.java,
                "neo.db"
            ).fallbackToDestructiveMigration().build()

    }
}