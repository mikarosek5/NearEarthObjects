package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.list


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "spaceX_launches")
data class Launch(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("net")
    val date: String

)