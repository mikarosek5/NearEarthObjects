package eu.invest.klk.neadearthobjects.ui.picture_of_day.big_picture

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.internal.GlideApp
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import eu.invest.klk.neadearthobjects.ui.picture_of_day.PictureOfDayViewModel
import eu.invest.klk.neadearthobjects.ui.picture_of_day.PictureOfDayViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.big_picture_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class BigPictureFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var viewModel: PictureOfDayViewModel
    private val pictureOfDayViewModelFactory: PictureOfDayViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.big_picture_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.toolbar?.visibility = View.GONE
        activity?.bottom_nav?.visibility = View.GONE
        viewModel = ViewModelProviders.of(this, pictureOfDayViewModelFactory).get(PictureOfDayViewModel::class.java)
        bindUi()

        val view = activity?.window?.decorView

        view?.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun bindUi() = launch {
        viewModel.daily.await().observe(this@BigPictureFragment, Observer {
            if (it == null)
                return@Observer
            loadImage(it.url)
        })
    }

    private fun loadImage(url: String) {
        GlideApp.with(this@BigPictureFragment).load(url).transition(DrawableTransitionOptions.withCrossFade()).listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    group_loading?.visibility = View.GONE
                    return false
                }
            }
        ).into(photo as ImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.toolbar?.visibility = View.VISIBLE
        activity?.bottom_nav?.visibility = View.VISIBLE
    }

}
