package eu.invest.klk.neadearthobjects.ui.pictureOfDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.internal.GlideApp
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.picture_of_day_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PictureOfDayFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: PictureOfDayViewModelFactory by instance()

    private lateinit var viewModel: PictureOfDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.picture_of_day_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PictureOfDayViewModel::class.java)
        bindUi()
    }

    private fun bindUi() = launch {
        val daily = viewModel.daily.await()
        daily.observe(this@PictureOfDayFragment, Observer {
            if (it == null)
                return@Observer
            group_loading.visibility = View.GONE
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.title
            description.text = it.explanation
            GlideApp.with(this@PictureOfDayFragment).load(it.url).into(imageView)

//            description.text = it.toString()
        })
    }

}
