package org.eshendo.soopra.model

data class ErrorObject(
        val message: String,
        val responseCode: Int = 0
)