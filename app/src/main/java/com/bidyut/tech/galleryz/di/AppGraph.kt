package com.bidyut.tech.galleryz.di

import android.annotation.SuppressLint
import android.content.Context
import com.bidyut.tech.galleryz.log.ArrowLogger
import com.bidyut.tech.galleryz.log.Logger
import com.bidyut.tech.galleryz.log.LoggerManager
import com.bidyut.tech.galleryz.log.ThickArrowLogger
import com.squareup.moshi.Moshi
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class AppGraph(
    val appContext: Context,
) {
    @Provides
    @AppScope
    fun moshi(): Moshi = Moshi.Builder().build()

    @Provides
    @AppScope
    fun logger(l: LoggerManager): Logger = l

    abstract val loggers: Set<Logger>

    @IntoSet
    @Provides
    @AppScope
    protected fun arrowLogger(l: ArrowLogger): Logger = l

    @IntoSet
    @Provides
    @AppScope
    protected fun thickArrowLogger(l: ThickArrowLogger): Logger = l

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
