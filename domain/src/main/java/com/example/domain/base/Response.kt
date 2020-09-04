package com.example.domain.base

// Like Either
sealed class Response<out F, out S> {
    data class Failure<out F>(val error: F) : Response<F, Nothing>()

    data class Success<out S>(val value: S) : Response<Nothing, S>()

    val isFailure get() = this is Failure<F>

    val isSuccess get() = this is Success<S>

    fun <F> failure(f: F) = Failure(f)

    fun <S> success(s: S) = Success(s)

    fun response(
        functionFailure: (F) -> Any,
        functionSuccess: (S) -> Any
    ): Any =
        when (this) {
            is Failure -> functionFailure(error)
            is Success -> functionSuccess(value)
        }
}