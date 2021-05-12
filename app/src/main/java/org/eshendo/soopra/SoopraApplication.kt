package org.eshendo.soopra

import android.app.Application
import org.eshendo.soopra.db.databseModule
import org.eshendo.soopra.network.networkModule
import org.eshendo.soopra.repo.LocalRepository
import org.eshendo.soopra.repo.LocalRepositoryImpl
import org.eshendo.soopra.repo.NetworkRepository
import org.eshendo.soopra.repo.NetworkRepositoryImpl
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModel
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModelImpl
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModel
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModelImpl
import org.eshendo.soopra.ui.fragments.movie.viemodel.MovieViewModel
import org.eshendo.soopra.ui.fragments.movie.viemodel.MovieViewModelImpl
import org.eshendo.soopra.usecases.network.NetworkUseCase
import org.eshendo.soopra.util.BaseViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SoopraApplication : Application() {

    private val viewModelModule = module {
        viewModel{ MainViewModelImpl() }
        viewModel{ AllMoviesViewModelImpl()}
        viewModel{ MovieViewModelImpl()}
    }

    private val repoModule = module {
        single<NetworkRepository> { NetworkRepositoryImpl(get()) }
        single<LocalRepository> { LocalRepositoryImpl(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SoopraApplication)
            modules(viewModelModule, repoModule, networkModule, databseModule)
        }
    }
}