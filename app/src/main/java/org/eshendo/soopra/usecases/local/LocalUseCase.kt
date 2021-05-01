package org.eshendo.soopra.usecases.local

import org.eshendo.soopra.repo.LocalRepository
import org.eshendo.soopra.usecases.UseCase
import org.koin.core.component.inject

abstract class LocalUseCase<T>: UseCase<T>(){
    protected val repo: LocalRepository by inject()
}