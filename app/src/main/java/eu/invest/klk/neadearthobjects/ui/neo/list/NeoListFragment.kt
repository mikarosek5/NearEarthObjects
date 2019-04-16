package eu.invest.klk.neadearthobjects.ui.neo.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import eu.invest.klk.neadearthobjects.R

class NeoListFragment : Fragment() {

    companion object {
        fun newInstance() = NeoListFragment()
    }

    private lateinit var viewModel: NeoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.neo_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NeoListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
