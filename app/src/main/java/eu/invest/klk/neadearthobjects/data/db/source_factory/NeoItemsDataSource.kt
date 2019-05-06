package eu.invest.klk.neadearthobjects.data.db.source_factory

import androidx.paging.PageKeyedDataSource
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.network.network_source.nasa.NasaNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NeoItemsDataSource(private val nasaNetWorkDataSource: NasaNetWorkDataSource) :
    PageKeyedDataSource<Int, NearEarthObject>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NearEarthObject>) {
        GlobalScope.launch(Dispatchers.IO) {
            callback.onResult(
                nasaNetWorkDataSource.fetchNeoObjectForDataSource(0, params.requestedLoadSize),
                null,
                1
            ) //Prev page 1 or nul, next 2 or 1
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NearEarthObject>) {
        GlobalScope.launch(Dispatchers.IO) {
            callback.onResult(
                nasaNetWorkDataSource.fetchNeoObjectForDataSource(params.key, params.requestedLoadSize),
                params.key + 1
            )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NearEarthObject>) {
        GlobalScope.launch(Dispatchers.IO) {
            val nextIndex = if (params.key > 1) params.key - 1 else null
            callback.onResult(
                nasaNetWorkDataSource.fetchNeoObjectForDataSource(params.key, params.requestedLoadSize),
                nextIndex
            )
        }
    }
}