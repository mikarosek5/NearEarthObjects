package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import eu.invest.klk.neadearthobjects.ui.spacex.launcheslist.recycler.LaunchAdapter
import kotlinx.android.synthetic.main.space_xlist_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class SpaceXListFragment : ScopedFragment(),KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory:SpaceXlistViewModelFactory by instance()
    private lateinit var viewModel: SpaceXlistViewModel
    private val adapter by lazy { LaunchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.space_xlist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(SpaceXlistViewModel::class.java)
        bindUi()
    }

    private fun bindUi()= launch {
        viewModel.launches.await().observe(this@SpaceXListFragment, Observer {
            if (it==null)
                return@Observer
            adapter.submitList(it)
            recycler.adapter = adapter
            group_loading.visibility = View.GONE
        })
    }

}
