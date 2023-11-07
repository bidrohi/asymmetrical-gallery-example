package com.bidyut.tech.galleryz.ui.gallery

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bidyut.tech.galleryz.di.AppGraph
import com.bidyut.tech.galleryz.di.RetainedScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@RetainedScope
@Component
abstract class GalleryGraph(
    @Component protected val parent: AppGraph,
) {
    abstract val ourViewModelFactory: ViewModelProvider.Factory

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
}
