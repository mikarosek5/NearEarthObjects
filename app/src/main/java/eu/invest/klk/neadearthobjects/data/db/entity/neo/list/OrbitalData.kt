package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName

data class OrbitalData(
    @SerializedName("aphelion_distance")
    val aphelionDistance: String,
    @SerializedName("ascending_node_longitude")
    val ascendingNodeLongitude: String,
    @SerializedName("data_arc_in_days")
    val dataArcInDays: Int,
    @SerializedName("eccentricity")
    val eccentricity: String,
    @SerializedName("epoch_osculation")
    val epochOsculation: String,
    @SerializedName("equinox")
    val equinox: String,
    @SerializedName("first_observation_date")
    val firstObservationDate: String,
    @SerializedName("inclination")
    val inclination: String,
    @SerializedName("jupiter_tisserand_invariant")
    val jupiterTisserandInvariant: String,
    @SerializedName("last_observation_date")
    val lastObservationDate: String,
    @SerializedName("mean_anomaly")
    val meanAnomaly: String,
    @SerializedName("mean_motion")
    val meanMotion: String,
    @SerializedName("minimum_orbit_intersection")
    val minimumOrbitIntersection: String,
    @SerializedName("observations_used")
    val observationsUsed: Int,
    @SerializedName("orbit_class")
    val orbitClass: OrbitClass,
    @SerializedName("orbit_determination_date")
    val orbitDeterminationDate: String,
    @SerializedName("orbit_id")
    val orbitId: String,
    @SerializedName("orbit_uncertainty")
    val orbitUncertainty: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    @SerializedName("perihelion_argument")
    val perihelionArgument: String,
    @SerializedName("perihelion_distance")
    val perihelionDistance: String,
    @SerializedName("perihelion_time")
    val perihelionTime: String,
    @SerializedName("semi_major_axis")
    val semiMajorAxis: String
)