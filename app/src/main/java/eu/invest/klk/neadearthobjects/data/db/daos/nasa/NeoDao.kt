package eu.invest.klk.neadearthobjects.data.db.daos.nasa

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.source_factory.NeoItemsDataSource

@Dao
interface NeoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(nearEarthObject: List<NearEarthObject>)

    @Query("select * from near_earth_objects")
    fun getAllNeoObjects(): LiveData<List<NearEarthObject>>

    @Query("select * from near_earth_objects")
    fun getAllNeoObjectsPaged(): DataSource.Factory<Int, NearEarthObject>

//    @Query("select * from near_earth_objects")
//    fun getAllNeoObjectsPaged():NeoItemsDataSource


}