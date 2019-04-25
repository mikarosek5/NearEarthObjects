package eu.invest.klk.neadearthobjects.ui.neo.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import eu.invest.klk.neadearthobjects.ui.helpers.EndlessScrollListener
import kotlinx.android.synthetic.main.neo_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class NeoListFragment : ScopedFragment(),KodeinAware {
    override val kodein: Kodein by closestKodein()

    private val neoListViewModelFactory:NeoListViewModelFactory by instance()

    private lateinit var viewModel: NeoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.neo_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,neoListViewModelFactory).get(NeoListViewModel::class.java)
        bindUi()

    }

    private fun bindUi() = launch {
        setUpToolbarText()
        setUpRecycler()
    }

    private suspend fun setUpRecycler(){
        val neoObjectList = viewModel.getAllNeos(203,20).value.await()
        neoObjectList.observe(this@NeoListFragment, Observer {
            if (it==null)
                return@Observer
            Log.d("Objects",it.toString())

            val groupAdaper = GroupAdapter<ViewHolder>().apply {
                addAll(it.map { item-> NeoItem(item) })
            }
            recycler.apply {
                layoutManager = LinearLayoutManager(this@NeoListFragment.context).also {layoutManager->
                    addOnScrollListener(EndlessScrollListener(layoutManager,loadNewDataListener))
                }
                adapter = groupAdaper
            }

        })
    }
    private val loadNewDataListener:()->Unit = {
//        Toast.makeText(this.context,"Dziala",Toast.LENGTH_SHORT).show()
        Log.d(this::class.java.simpleName,"koniec")
    }



    private suspend fun setUpToolbarText(){
        val neoObject = viewModel.neoCount.await()
        neoObject.observe(this@NeoListFragment, Observer {
            if (it==null)
                return@Observer
            (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.near_object_count,it.nearEarthObjectCount)
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.near_object_count_closest,it.closeApproachCount)

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
    }

}
