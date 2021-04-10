package org.eshendo.soopra.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@Suppress("PropertyName")
abstract class BaseViewModel<T> : ViewModel() {
    protected val _viewState = MutableLiveData<T>()
    val viewState: LiveData<T> get() = _viewState
}