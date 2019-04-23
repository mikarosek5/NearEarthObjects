package eu.invest.klk.neadearthobjects.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject

@Dao
interface NeoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(nearEarthObject: List<NearEarthObject>)

    @Query("select * from near_earth_objects")
    fun getAllNeoObjects():LiveData<List<NearEarthObject>>

}