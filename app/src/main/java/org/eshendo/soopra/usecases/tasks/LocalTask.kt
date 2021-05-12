package org.eshendo.soopra.usecases.tasks

import org.eshendo.soopra.model.ErrorObject
import kotlin.reflect.KFunction

class LocalTask<T>(
    private val func: () -> T?
) : BaseTask() {

    override  fun execute() {
        val r = func()
        if (r == null){
            val error = ErrorObject(message = ":(")
            onResult(error)
        }else{
            onResult(r)
        }
    }
}