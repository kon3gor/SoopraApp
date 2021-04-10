package org.eshendo.soopra.usecases

import org.eshendo.soopra.model.ErrorObject

abstract class UseCase<T>(
        val onResult: (T) -> Unit,
        val onError: (ErrorObject) -> Unit
) {

    abstract fun fetchData()

    protected fun gotResult(data: T){
        onResult(data)
    }

    protected fun gotError(message: String, code: Int = 0){
        val error = ErrorObject(message, code)
        onError(error)
    }

}