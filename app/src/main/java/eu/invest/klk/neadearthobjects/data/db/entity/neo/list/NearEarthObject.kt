package eu.invest.klk.neadearthobjects.data.db.entity.neo.list

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "near_earth_objects")
data class NearEarthObject(
    @SerializedName("neo_reference_id")
    @PrimaryKey(autoGenerate = false)
    val neoReferenceId: String,
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    @SerializedName("is_sentry_object")
    val isSentryObject: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("estimated_diameter")
    @Embedded(prefix = "estimated_diameter_")
    val estimatedDiameter: EstimatedDiameter,
    @SerializedName("orbital_data")
    @Embedded(prefix = "orbital_data_")
    val orbitalData: OrbitalData
)