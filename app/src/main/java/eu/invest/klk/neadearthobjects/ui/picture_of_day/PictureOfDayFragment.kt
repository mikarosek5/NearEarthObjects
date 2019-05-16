package eu.invest.klk.neadearthobjects.ui.picture_of_day

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.internal.GlideApp
import eu.invest.klk.neadearthobjects.internal.Status
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.activity_main.*
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
        status()
        refresh()
    }


    private fun status() = launch {
        val status = viewModel.status.await() as LiveData<Status>
        status.observe(this@PictureOfDayFragment, Observer {
            if (it == Status.ERROR) {
                Snackbar.make(
                    this@PictureOfDayFragment.cardView,
                    "Connection issue, connect device to internet then swipe to refresh",
                    Snackbar.LENGTH_LONG
                ).show()
                refresh?.isRefreshing = false
                group_loading.visibility = View.GONE
            }
        })
    }

    private fun refresh() {
        refresh?.setOnRefreshListener {
            launch {
                viewModel.refresh()
            }
        }
    }

    private fun bindUi() = launch {
        val daily = viewModel.daily.await()
        imageNavigation()
        daily.observe(this@PictureOfDayFragment, Observer {
            if (it == null)
                return@Observer
//            group_loading.visibility = View.GONE
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.title
            description.text = it.explanation
            loadImage(it.url)
        })
    }

    private fun imageNavigation() {
        imageView?.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PictureOfDayFragmentDirections.actionPictureOfDayFragmentToBigPictureFragment())
            activity?.toolbar?.visibility = View.GONE
            activity?.bottom_nav?.visibility = View.GONE
        }
    }

    private fun loadImage(url: String) {
        GlideApp.with(this@PictureOfDayFragment).load(url).apply {
            transition(DrawableTransitionOptions.withCrossFade())
            listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    group_loading.visibility = View.GONE
                    refresh?.isRefreshing = false
                    cardView.animate().alpha(1f).duration = 2
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    group_loading.visibility = View.GONE
                    refresh?.isRefreshing = false
                    cardView.animate().alpha(1f).duration = 2
                    return false
                }

            })
            into(imageView)
        }
    }


}
