package eu.invest.klk.neadearthobjects.data.db.daos.nasa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NEOID
import eu.invest.klk.neadearthobjects.data.db.entity.neo.count.NeoCount

@Dao
interface NeoCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(neoCount: NeoCount)

    @Query("select * from neo_objects_count where id = $NEOID ")
    fun getNeo ():LiveData<NeoCount>

}