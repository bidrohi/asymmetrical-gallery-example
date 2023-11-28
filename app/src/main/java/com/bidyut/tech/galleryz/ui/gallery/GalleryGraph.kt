package com.bidyut.tech.galleryz.ui.gallery

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bidyut.tech.galleryz.di.RetainedScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Provider

@RetainedScope
@Subcomponent(
    modules = [GalleryGraph.Bindings::class]
)
interface GalleryGraph {
    val ourViewModelFactory: ViewModelProvider.Factory

    @Module
    class Bindings {
        @Provides
        @RetainedScope
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

    @Subcomponent.Builder
    interface Builder {
        fun build(): GalleryGraph
    }
}
