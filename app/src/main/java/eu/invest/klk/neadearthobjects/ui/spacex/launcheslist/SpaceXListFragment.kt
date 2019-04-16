package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import eu.invest.klk.neadearthobjects.R

class SpaceXListFragment : Fragment() {

    companion object {
        fun newInstance() = SpaceXListFragment()
    }

    private lateinit var viewModel: SpaceXlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.space_xlist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpaceXlistViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
