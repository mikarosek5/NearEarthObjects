package eu.invest.klk.neadearthobjects.ui.neo.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import eu.invest.klk.neadearthobjects.R

class NeoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = NeoDetailsFragment()
    }

    private lateinit var viewModel: NeoDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.neo_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NeoDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
