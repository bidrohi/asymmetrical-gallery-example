package com.bidyut.tech.galleryz.log

import android.util.Log
import me.tatarka.inject.annotations.Inject

@Inject
class ThickArrowLogger: Logger {
    override fun log(message: String) {
        Log.d("=>", message)
    }
}
