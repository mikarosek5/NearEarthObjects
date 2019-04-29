package eu.invest.klk.neadearthobjects.data.db.boundary_callback

import android.util.Log
import androidx.paging.PagedList
import eu.invest.klk.neadearthobjects.data.db.NeoDao
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.network.NasaNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NeoBoundaryCallback(private val nasaNetworkDatasource: NasaNetWorkDataSource, private val neoDao: NeoDao) :
    PagedList.BoundaryCallback<NearEarthObject>() {
    override fun onItemAtEndLoaded(itemAtEnd: NearEarthObject) {
        super.onItemAtEndLoaded(itemAtEnd)
        //Will implement network + database configuration to cache neo objects
    }
}