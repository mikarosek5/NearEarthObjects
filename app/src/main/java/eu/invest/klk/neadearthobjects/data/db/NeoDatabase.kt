package eu.invest.klk.neadearthobjects.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.try_modular.neoResponse.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.daily.Daily
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount


@Database(
    entities = [Daily::class, NeoCount::class,NearEarthObject::class],
    version = 1,
    exportSchema = false //TODO("check this warning later")
)
abstract class NeoDatabase:RoomDatabase() {
    abstract fun dailyDao():DailyDao
    abstract fun neoCountDao():NeoCountDao
    abstract fun neoDao():NeoDao

    companion object{
        @Volatile var instance: NeoDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,NeoDatabase::class.java,"neo.db").build()

    }
}