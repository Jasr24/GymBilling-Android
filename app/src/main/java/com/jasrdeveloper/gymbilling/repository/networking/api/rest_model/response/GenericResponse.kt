package com.jasrdeveloper.gymbilling.repository.networking.api.rest_model.response

data class GenericResponse<T>(
    var success: Boolean? = null,
    var error: Boolean? = null,
    var message: String? = null,
    var data: T? = null
)