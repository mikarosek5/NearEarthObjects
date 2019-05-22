package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.internal.Status
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import eu.invest.klk.neadearthobjects.ui.spacex.launcheslist.recycler.LaunchAdapter
import kotlinx.android.synthetic.main.space_xlist_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class SpaceXListFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: SpaceXlistViewModelFactory by instance()
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpaceXlistViewModel::class.java)
        bindUi()
        status()
        refresh()
    }

    private fun status() = launch {
        val status = viewModel.status.await() as LiveData<Status>
        status.observe(this@SpaceXListFragment, Observer {
            if (it == Status.ERROR) {
                Snackbar.make(
                    this@SpaceXListFragment.recycler,
                    "Connection issue, connect device to internet then swipe to refresh",
                    Snackbar.LENGTH_LONG
                ).show()
                refresh?.isRefreshing = false
            }

        })
    }

    private fun bindUi() = launch {
        viewModel.launches.await().observe(this@SpaceXListFragment, Observer {
            if (it == null||it.isEmpty())
                return@Observer
            adapter.submitList(it)
            recycler.adapter = adapter
            group_loading.visibility = View.GONE
            refresh?.isRefreshing = false
        })
    }

    private fun refresh() {
        refresh?.setOnRefreshListener {
            launch { viewModel.refresh() }
        }
    }

}
