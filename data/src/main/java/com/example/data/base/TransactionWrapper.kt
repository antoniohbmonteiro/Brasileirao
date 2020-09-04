package com.example.data.base

import com.example.domain.base.Cause
import com.example.domain.base.Response
import retrofit2.HttpException


suspend fun <F> transactionWrapper(
    call: suspend () -> F
): Response<Cause, F> {
    return try {
        Response.Success(call())
    } catch (exception: Exception) {
        Response.Failure(Cause.Local(exception))
    }
}