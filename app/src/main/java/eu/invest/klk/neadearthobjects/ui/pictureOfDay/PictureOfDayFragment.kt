package eu.invest.klk.neadearthobjects.ui.pictureOfDay

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.network.ConnectivityInterceptorImpl
import eu.invest.klk.neadearthobjects.data.network.NasaNetWorkDataSourceImpl
import eu.invest.klk.neadearthobjects.data.network.NasaService
import kotlinx.android.synthetic.main.picture_of_day_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PictureOfDayFragment : Fragment() {


    private lateinit var viewModel: PictureOfDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.picture_of_day_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PictureOfDayViewModel::class.java)
        // TODO: Use the ViewModel
        val abc =  NasaNetWorkDataSourceImpl(NasaService(ConnectivityInterceptorImpl(this@PictureOfDayFragment.context!!)))
        abc.downloadedDaily.observe(this, Observer {
            textView.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.Main) {
            abc.fetchDaily()
        }
    }

}
