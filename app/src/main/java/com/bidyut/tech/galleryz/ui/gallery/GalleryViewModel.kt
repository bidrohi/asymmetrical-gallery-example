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
    fun getItems(ctx: Context, columns: Int): LiveData<List<Item>> {
        return Transformations.map(GalleryRepository().getResponse(ctx)) { r ->
            val list = ArrayList<Item>()
            val row = ArrayList<Item>()
            var rowRatios = 0f
            r!!.value!!.forEach { it: Result ->
                val imageRatio = it.width!! / it.height!!.toFloat()
                val item = Item(
                        it.imageId as ItemId,
                        it.name!!,
                        Color.parseColor("#" + it.accentColor),
                        it.thumbnailUrl!!,
                        imageRatio
                )
                list.add(item)
                rowRatios += item.imageRatio
                if (rowRatios > 2f) {
                    var used = 0
                    row.forEach { it2: Item ->
                        it2.columns = ((columns * it2.imageRatio) / rowRatios).toInt()
                        used += it2.columns
                    }
                    item.columns = columns - used
                    row.clear()
                    rowRatios = 0f
                } else {
                    row.add(item)
                }
            }
            list
        }
    }
}
