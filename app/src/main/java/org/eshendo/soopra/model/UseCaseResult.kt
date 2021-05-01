package org.eshendo.soopra.model

class UseCaseResult<T>(
    val error: ErrorObject? = null,
    val data: T? = null
)