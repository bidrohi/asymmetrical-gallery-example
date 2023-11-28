package com.bidyut.tech.galleryz.log

import android.util.Log
import com.bidyut.tech.galleryz.di.AppScope
import javax.inject.Inject

@AppScope
class ThickArrowLogger @Inject constructor(): Logger {
    override fun log(message: String) {
        Log.d("=>", message)
    }
}
