package eu.invest.klk.neadearthobjects.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.invest.klk.neadearthobjects.data.db.entity.NEOID
import eu.invest.klk.neadearthobjects.data.db.entity.NeoCount

@Dao
interface NeoCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(neoCount: NeoCount)

    @Query("select * from neo_objects_count where id = $NEOID ")
    fun getNeo ():LiveData<NeoCount>

}