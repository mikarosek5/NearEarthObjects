package eu.invest.klk.neadearthobjects.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.DAILY_ID
import eu.invest.klk.neadearthobjects.data.db.entity.Daily

@Dao
interface DailyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(daily: Daily)

    @Query("select * from daily where id = $DAILY_ID")
    fun getDaily():LiveData<Daily>
}