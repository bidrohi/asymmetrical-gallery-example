package com.bidyut.tech.galleryz.di

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bidyut.tech.galleryz.log.ArrowLogger
import com.bidyut.tech.galleryz.log.Logger
import com.bidyut.tech.galleryz.log.LoggerManager
import com.bidyut.tech.galleryz.log.ThickArrowLogger
import com.bidyut.tech.galleryz.ui.gallery.GalleryViewModel
import com.squareup.moshi.Moshi
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppGraph(
    val appContext: Context,
) {
    @Provides
    protected fun moshi(): Moshi = Moshi.Builder().build()

    @Provides
    protected fun logger(l: LoggerManager): Logger = l

    protected abstract val loggers: Set<Logger>

    @IntoSet
    @Provides
    protected fun arrowLogger(l: ArrowLogger): Logger = l

    @IntoSet
    @Provides
    protected fun thickArrowLogger(l: ThickArrowLogger): Logger = l

    @Provides
    protected fun viewModelProviderFactory(
        galleryViewModel: () -> GalleryViewModel,
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            addInitializer(GalleryViewModel::class) {
                galleryViewModel()
            }
        }
    }

    abstract val ourViewModelFactory: ViewModelProvider.Factory

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
