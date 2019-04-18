package eu.invest.klk.neadearthobjects

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import eu.invest.klk.neadearthobjects.data.db.DailyDao
import eu.invest.klk.neadearthobjects.data.db.NeoDatabase
import eu.invest.klk.neadearthobjects.data.network.*
import eu.invest.klk.neadearthobjects.data.repository.NeoRepository
import eu.invest.klk.neadearthobjects.data.repository.NeoRepositoryImpl
import eu.invest.klk.neadearthobjects.ui.neo.list.NeoListViewModelFactory
import eu.invest.klk.neadearthobjects.ui.pictureOfDay.PictureOfDayViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class NeoApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@NeoApplication))

        bind() from singleton { NeoDatabase(instance()) }
        bind() from singleton { instance<NeoDatabase>().dailyDao() }
        bind() from singleton { instance<NeoDatabase>().neoCountDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { NasaService(instance()) }
        bind<NasaNetWorkDataSource>() with singleton { NasaNetWorkDataSourceImpl(instance()) }
        bind<NeoRepository>() with singleton { NeoRepositoryImpl(instance(),instance(),instance(),instance()) }
        bind() from provider { PictureOfDayViewModelFactory(instance()) }
        bind() from provider { NeoListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}