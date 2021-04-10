package org.eshendo.soopra.util

interface Screen<T> {
    fun updateState(state: T)
    fun observe()
}