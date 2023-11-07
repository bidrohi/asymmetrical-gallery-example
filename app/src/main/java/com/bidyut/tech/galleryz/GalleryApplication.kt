package com.bidyut.tech.galleryz

import android.app.Application
import com.bidyut.tech.galleryz.di.AppGraph
import com.bidyut.tech.galleryz.di.create

class GalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppGraph.assign(AppGraph::class.create(applicationContext))
    }
}
