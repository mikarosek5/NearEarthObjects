package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.network.interceptors.ConnectivityInterceptorImpl
import eu.invest.klk.neadearthobjects.data.network.services.LaunchLibrary
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class SpaceXListFragment : ScopedFragment() {


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
        launch{
            val a = LaunchLibrary(ConnectivityInterceptorImpl(this@SpaceXListFragment.context!!)).downloadNextLaunchesAsync(5,"falcon").await()
            val outputFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss", Locale.US)
            val abc = LocalDateTime.parse(a.launches.last().date.dropLast(4),outputFormat)
            Log.d(this@SpaceXListFragment::class.java.simpleName,abc.toLocalDate().plusDays(4).toString())
        }
    }

}
