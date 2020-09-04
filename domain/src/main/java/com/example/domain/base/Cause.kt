package com.example.domain.base

sealed class Cause(val throwable: Throwable? = null){
    class Remote(throwable: Throwable? = null) : Cause(throwable)
    class Local(throwable: Throwable? = null) : Cause(throwable)
    class Unknown(throwable: Throwable? = null) : Cause(throwable)
}

