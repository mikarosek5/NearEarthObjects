package eu.invest.klk.neadearthobjects.ui.spacex.next_launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.space_xdetails_fragment.*

class SpaceXDetails : ScopedFragment() {

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
        arguments?.let {
            val safeArgs = SpaceXDetailsArgs.fromBundle(it)
            tv.text = "args from ${safeArgs.launchId}"
        }
    }

}
