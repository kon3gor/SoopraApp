package org.eshendo.soopra.usecases.tasks

abstract class BaseTask{

    private var callback: ((Any) -> Unit)? = null

    abstract fun execute()

    fun doOnResult(
        result: (Any) -> Unit
    ) : BaseTask {
        this.callback = result
        return this
    }

    fun onResult(data: Any){
        callback?.invoke(data)
    }
}