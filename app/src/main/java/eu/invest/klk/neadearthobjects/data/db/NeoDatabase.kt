package eu.invest.klk.neadearthobjects.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.invest.klk.neadearthobjects.data.db.entity.Daily


@Database(
    entities = [Daily::class],
    version = 1,
    exportSchema = false //TODO("check this warning later")
)
abstract class NeoDatabase:RoomDatabase() {
    abstract fun dailyDao():DailyDao

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