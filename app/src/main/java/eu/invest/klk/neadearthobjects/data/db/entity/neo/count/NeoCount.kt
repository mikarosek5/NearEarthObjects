package eu.invest.klk.neadearthobjects.data.db.entity.neo.count

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val NEOID = 0

@Entity(tableName = "neo_objects_count")
data class NeoCount(
    @SerializedName("close_approach_count")
    val closeApproachCount: Int,
    @SerializedName("near_earth_object_count")
    val nearEarthObjectCount: Int
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = NEOID
}