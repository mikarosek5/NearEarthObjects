package eu.invest.klk.neadearthobjects.data.db.boundary_callback

import androidx.paging.PagedList
import eu.invest.klk.neadearthobjects.data.db.daos.nasa.NeoDao
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.network.network_source.nasa.NasaNetWorkDataSource

class NeoBoundaryCallback(private val nasaNetworkDatasource: NasaNetWorkDataSource, private val neoDao: NeoDao) :
    PagedList.BoundaryCallback<NearEarthObject>() {
    override fun onItemAtEndLoaded(itemAtEnd: NearEarthObject) {
        super.onItemAtEndLoaded(itemAtEnd)
        //Will implement network + database configuration to cache neo objects
    }
}