package eu.invest.klk.neadearthobjects.data.db.daos.spacex

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: Launch)

    @Query("select * from spaceX_launches")
    fun getAllLaunches():LiveData<List<Launch>>
}