package eu.invest.klk.neadearthobjects.ui.spacex.launchdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import eu.invest.klk.neadearthobjects.R

class SpaceXDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SpaceXDetailsFragment()
    }

    private lateinit var viewModel: SpaceXdetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.space_xdetails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpaceXdetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
