package com.example.data.base

import com.example.domain.base.Cause
import com.example.domain.base.Response
import retrofit2.HttpException

suspend fun <F> requestWrapper(
    call: suspend () -> F
): Response<Cause, F> {
    return try {
        Response.Success(call())
    } catch (exception: Exception) {
        return when (exception) {
            is HttpException -> Response.Failure(Cause.Remote(exception))
            else -> Response.Failure(Cause.Unknown(exception))
        }
    }
}