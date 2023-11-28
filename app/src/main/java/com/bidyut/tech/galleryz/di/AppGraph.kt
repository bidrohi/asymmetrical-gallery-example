package com.bidyut.tech.galleryz.di

import android.annotation.SuppressLint
import android.content.Context
import com.bidyut.tech.galleryz.log.ArrowLogger
import com.bidyut.tech.galleryz.log.Logger
import com.bidyut.tech.galleryz.log.LoggerManager
import com.bidyut.tech.galleryz.log.ThickArrowLogger
import com.bidyut.tech.galleryz.ui.gallery.GalleryGraph
import com.squareup.moshi.Moshi
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@AppScope
@Component(
    modules = [AppGraph.Bindings::class],
)
interface AppGraph {
    val galleryGraphBuilder: GalleryGraph.Builder

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
