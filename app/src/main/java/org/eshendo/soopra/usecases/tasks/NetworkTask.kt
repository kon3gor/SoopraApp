package org.eshendo.soopra.usecases.tasks

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.eshendo.soopra.model.ErrorObject
import io.reactivex.rxjava3.kotlin.subscribeBy

class NetworkTask<T : Any>(
    private val task: Observable<T>
) : BaseTask() {

    override fun execute() {

        task.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    val error = ErrorObject(it.localizedMessage ?: "")
                    onResult(error)
                },
                onNext = {
                    onResult(it)
                }
            )
    }
}