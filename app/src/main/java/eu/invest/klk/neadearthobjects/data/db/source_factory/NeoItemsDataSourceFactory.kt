package eu.invest.klk.neadearthobjects.data.db.source_factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.network.network_source.nasa.NasaNetWorkDataSource

class NeoItemsDataSourceFactory(private val nasaNetWorkDataSource: NasaNetWorkDataSource) :
    DataSource.Factory<Int, NearEarthObject>() {

    private val liveDataSource = MutableLiveData<NeoItemsDataSource>()

    override fun create(): DataSource<Int, NearEarthObject> {
        NeoItemsDataSource(nasaNetWorkDataSource).apply {
            liveDataSource.postValue(this)
            return this
        }
    }
    suspend fun invalidateSource(){
        liveDataSource.value?.invalidate()
    }
}