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
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Provider

@AppScope
@Component(modules = [AppGraph.Bindings::class])
interface AppGraph {
    val loggers: Set<@JvmSuppressWildcards Logger>

    val ourViewModelFactory: ViewModelProvider.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setAppContext(appContext: Context): Builder

        fun build(): AppGraph
    }

    @Module
    class Bindings {
        @Provides
        @AppScope
        fun moshi(): Moshi = Moshi.Builder().build()

        @Provides
        fun logger(l: LoggerManager): Logger = l

        @Provides
        @IntoSet
        fun arrowLogger(l: ArrowLogger): Logger = l

        @Provides
        @IntoSet
        fun thickArrowLogger(l: ThickArrowLogger): Logger = l

        @Provides
        @AppScope
        fun viewModelProviderFactory(
            galleryViewModel: Provider<GalleryViewModel>,
        ): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(GalleryViewModel::class) {
                    galleryViewModel.get()
                }
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
