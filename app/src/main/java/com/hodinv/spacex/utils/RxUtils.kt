package com.hodinv.spacex.utils

import android.util.Log
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

fun setupRxErrorHandler(onUnknownError: (Throwable) -> Unit = {}) {
    RxJavaPlugins.setErrorHandler { error ->
        var wrappedError = error
        if (error is UndeliverableException) {
            error.cause?.apply {
                wrappedError = this
            }
        }

        when (wrappedError) {
            is NullPointerException,
            is IllegalArgumentException,
            is IllegalStateException ->
                Thread.currentThread().uncaughtExceptionHandler.uncaughtException(
                    Thread.currentThread(), wrappedError
                )
            is IOException,
            is SocketException,
            is InterruptedException -> {
            }
            else -> {
                onUnknownError(wrappedError)
                Log.w("ERROR", "Undeliverable exception received, not sure what to do $wrappedError")
            }
        }
    }
}
