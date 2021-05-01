package org.eshendo.soopra.usecases.network

import org.eshendo.soopra.repo.NetworkRepository
import org.eshendo.soopra.usecases.UseCase
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.NetworkTask
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class NetworkUseCase<T>: UseCase<T>(){
    protected val repo: NetworkRepository by inject()
}