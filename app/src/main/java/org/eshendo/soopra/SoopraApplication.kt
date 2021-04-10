package org.eshendo.soopra

import android.app.Application
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModel
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModelImpl
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModel
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SoopraApplication : Application() {

    private val viewModelModule = module {
        viewModel{ MainViewModelImpl() as MainViewModel }
        viewModel{ AllMoviesViewModelImpl() as AllMoviesViewModel }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SoopraApplication)
            modules(viewModelModule)
        }
    }
}