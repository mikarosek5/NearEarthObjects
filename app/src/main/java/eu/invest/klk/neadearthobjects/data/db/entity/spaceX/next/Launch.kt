package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "spaceX_launches")
data class Launch(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("net")
    val date: String
){
//    val fetchDate:ZonedDateTime = ZonedDateTime.now()
}