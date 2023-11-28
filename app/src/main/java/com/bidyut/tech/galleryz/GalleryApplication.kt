package com.bidyut.tech.galleryz

import android.app.Application
import com.bidyut.tech.galleryz.di.AppGraph
import com.bidyut.tech.galleryz.di.DaggerAppGraph

class GalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppGraph.assign(DaggerAppGraph.builder()
            .setAppContext(applicationContext)
            .build())
    }
}
