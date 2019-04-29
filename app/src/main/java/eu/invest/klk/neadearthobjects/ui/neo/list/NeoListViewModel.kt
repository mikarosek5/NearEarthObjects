package eu.invest.klk.neadearthobjects.ui.neo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.internal.lazyDeferred
import kotlinx.coroutines.Deferred

class NeoListViewModel(private val repository: NeoRepository) : ViewModel() {
    val neoCount by lazyDeferred { repository.getNeoCount() }
    suspend fun getAllNeos(page:Int,size:Int=20):Lazy<Deferred<LiveData<List<NearEarthObject>>>>{
        return lazyDeferred { repository.getNeoObjectsList(page = page,size = size) }
    }
    val pagedAllNeos = lazyDeferred { repository.getNeoObjectsListPaged() }

    fun refreshRecycler(){
        repository.invalidateNeoObjectsListPaged()
    }
}
