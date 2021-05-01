package org.eshendo.soopra.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.eshendo.soopra.model.ErrorObject
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import java.lang.IllegalStateException

@KoinApiExtension
abstract class UseCase<T> : KoinComponent {

    abstract var task: BaseTask

    @Suppress("UNCHECKED_CAST")
    protected fun run() : LiveData<UseCaseResult<T>>{
        val liveData = MutableLiveData<UseCaseResult<T>>()
        task.doOnResult {
            when (it){
                is ErrorObject -> {
                    val r = UseCaseResult<T>(error = it)
                    liveData.postValue(r)
                }
                else -> {
                    val r = UseCaseResult(data = it as T)
                    liveData.postValue(r)
                }
            }
        }.execute()

        return liveData
    }

    open fun proccessResult(r: UseCaseResult<T>){

    }

    abstract fun execute() : LiveData<UseCaseResult<T>>
}