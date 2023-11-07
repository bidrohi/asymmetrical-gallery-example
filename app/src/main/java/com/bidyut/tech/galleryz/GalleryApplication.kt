package com.bidyut.tech.galleryz

import android.app.Application
import com.bidyut.tech.galleryz.di.AppGraph

class GalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppGraph.assign(AppGraph(applicationContext))
    }
}
