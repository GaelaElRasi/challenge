package com.gaelaelrasi.dogbreeds.util

import android.content.res.Resources
import com.gaelaelrasi.dogbreeds.R
import retrofit2.HttpException
import java.io.IOException

class DefaultErrorHandler(private val resourceManager: Resources) {

    fun getMessage(error: Throwable): String {
        return when(error){
            is HttpException -> when (error.code()){
                400 -> error.message()
                403 -> error.message()
                404 -> error.message()
                500 -> resourceManager.getString(R.string.server_error)
                else -> resourceManager.getString(R.string.unknown_error)
            }
            is IOException -> resourceManager.getString(R.string.network_error)
            else -> resourceManager.getString(R.string.unknown_error)
        }
    }
}
