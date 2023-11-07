package com.bidyut.tech.galleryz.di

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bidyut.tech.galleryz.data.GalleryRepository
import com.bidyut.tech.galleryz.ui.gallery.GalleryViewModel
import com.squareup.moshi.Moshi

class AppGraph(
    val appContext: Context,
) {
    val moshi by lazy { Moshi.Builder().build() }

    val galleryRepository by lazy { GalleryRepository(moshi) }

    val ourViewModelFactory by lazy {
        viewModelFactory {
            addInitializer(GalleryViewModel::class) {
                GalleryViewModel(galleryRepository)
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: AppGraph
            private set

        fun assign(
            graph: AppGraph,
        ) {
            instance = graph
        }
    }
}
