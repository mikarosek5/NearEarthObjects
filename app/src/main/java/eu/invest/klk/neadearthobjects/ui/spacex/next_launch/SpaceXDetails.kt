package eu.invest.klk.neadearthobjects.ui.spacex.next_launch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details.Mission
import eu.invest.klk.neadearthobjects.internal.GlideApp
import eu.invest.klk.neadearthobjects.internal.Status
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import eu.invest.klk.neadearthobjects.ui.spacex.next_launch.recycler.mission.MissionAdapter
import kotlinx.android.synthetic.main.space_xdetails_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SpaceXDetails : ScopedFragment(), KodeinAware {

    private lateinit var viewModel: SpaceXdetailsViewModel
    override val kodein by closestKodein()
    private val viewModelFactory: SpaceXdetailsViewModelFactory by instance()
    private val missionAdapter by lazy { MissionAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.space_xdetails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpaceXdetailsViewModel::class.java)
        arguments?.let {
            val safeArgs = SpaceXDetailsArgs.fromBundle(it)
            status()
            bindUi(safeArgs.launchId)
        }
    }

    private fun bindUi(id: Int) = launch {
        LinearSnapHelper().attachToRecyclerView(mission_recycler)
        viewModel.getDetails(id).value.await().observe(this@SpaceXDetails, Observer { launche ->
            if (launche == null)
                return@Observer
            (activity as? AppCompatActivity)?.supportActionBar?.title = launche.name.dropLastWhile { it.isLetter() }.dropLast(2)
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = launche.name.dropWhile { (it.isWhitespace()||it.isLetterOrDigit())  }.drop(1)
            bindPlayButton(launche.vidURLs.firstOrNull())
            bindWikipediaButton(launche.lsp.wikiURL)
            loadImg(launche.rocket.imageURL)
            bindMissionRecycler(launche.missions)
        })
    }

    private fun bindPlayButton(url: String?) {
        if (url == null) {
            youtube.visibility = View.GONE
            return
        }
        youtube.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browser)
        }
    }

    private fun bindWikipediaButton(url: String?) {
        if (url == null) {
            wikipedia.visibility = View.GONE
            return

        }
        wikipedia.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browser)
        }
    }
    private fun loadImg(url:String){
        GlideApp.with(this).load(url).into(rocket_img)
    }
    private fun bindMissionRecycler(missionList:List<Mission>){
        if (missionList.isNullOrEmpty())
            noDataTxt.visibility = View.VISIBLE
        missionAdapter.submitList(missionList)
        mission_recycler.adapter = missionAdapter
    }
    private fun status()=launch{
        (viewModel.status.await() as LiveData<Status>).observe(this@SpaceXDetails, Observer {
            if (it==null)
                return@Observer
            if (it ==Status.ERROR){
                Navigation.findNavController(mission_recycler).navigate(SpaceXDetailsDirections.actionSpaceXDetailsToErrorFragment())
                Log.d("AAAAAAAAAAA",it.name)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null
    }
}
