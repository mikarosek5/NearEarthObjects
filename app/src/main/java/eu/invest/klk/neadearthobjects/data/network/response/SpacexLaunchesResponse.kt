package eu.invest.klk.neadearthobjects.data.network.response


import com.google.gson.annotations.SerializedName
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch

data class SpacexLaunchesResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("launches")
    val launches: List<Launch>
)