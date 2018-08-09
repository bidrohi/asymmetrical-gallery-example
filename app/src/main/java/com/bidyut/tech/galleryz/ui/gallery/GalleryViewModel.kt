package com.bidyut.tech.galleryz.ui.gallery

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bidyut.tech.galleryz.data.GalleryRepository
import com.bidyut.tech.galleryz.model.Item
import com.bidyut.tech.galleryz.model.ItemId
import com.bidyut.tech.galleryz.model.Result


class GalleryViewModel : ViewModel() {
    private val repository = GalleryRepository()

    fun getItems(ctx: Context): LiveData<List<Item>> {
        return Transformations.map(repository.getResponse(ctx)) { r ->
            val list = ArrayList<Item>()
            if (r != null) {
                r.value!!.forEach { it: Result ->
                    val imageRatio = it.width!! / it.height!!.toFloat()
                    val item = Item(
                            it.imageId as ItemId,
                            it.name!!,
                            Color.parseColor("#" + it.accentColor),
                            it.thumbnailUrl!!,
                            imageRatio
                    )
                    list.add(item)
                }
            }
            list
        }
    }
}
