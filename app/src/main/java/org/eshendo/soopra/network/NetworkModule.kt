package org.eshendo.soopra.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.eshendo.soopra.util.SERVER_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    factory { provideTMDbService(get()) }

}

private fun provideRetrofit() : Retrofit {
    return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(SERVER_URL)
            .build()
}

private fun provideTMDbService(retrofit: Retrofit) : TMDbService{
    return retrofit.create(TMDbService::class.java)
}