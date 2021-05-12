package org.eshendo.soopra.usecases.local

import org.eshendo.soopra.repo.LocalRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

abstract class SuspendUseCase<T, R> : KoinComponent {
    protected val repo: LocalRepository by inject()

    abstract suspend fun execute(data: T) : R

}