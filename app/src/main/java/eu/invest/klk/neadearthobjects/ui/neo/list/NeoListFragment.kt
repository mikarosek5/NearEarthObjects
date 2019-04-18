package eu.invest.klk.neadearthobjects.ui.neo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
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
