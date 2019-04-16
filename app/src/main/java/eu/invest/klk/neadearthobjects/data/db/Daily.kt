package eu.invest.klk.neadearthobjects.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val DAILY_ID = 0

@Entity(tableName = "daily_my")
data class Daily(
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = DAILY_ID

}
