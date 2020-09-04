package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response

interface UseCase<R, in Params > {
    suspend fun execute(params : Params ): Response<Cause, R>

    class None
}