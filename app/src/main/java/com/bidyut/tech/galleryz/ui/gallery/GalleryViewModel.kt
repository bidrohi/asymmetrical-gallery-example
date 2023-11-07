package com.bidyut.tech.galleryz.ui.gallery

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.bidyut.tech.galleryz.data.GalleryRepository
import com.bidyut.tech.galleryz.log.ArrowLogger
import com.bidyut.tech.galleryz.model.Item
import com.bidyut.tech.galleryz.model.ItemId
import com.bidyut.tech.galleryz.model.Result
import me.tatarka.inject.annotations.Inject


@Inject
class GalleryViewModel(
    private val repository: GalleryRepository,
    private val logger: ArrowLogger,
) : ViewModel() {
    fun getItems(ctx: Context, columns: Int): LiveData<List<Item>> {
        return repository.getResponse(ctx).map { r ->
            val list = mutableListOf<Item>()
            val row = mutableListOf<Item>()
            var rowRatios = 0f
            r.value?.forEach { it: Result ->
                if (it.width == null || it.height == null) return@forEach
                val imageRatio = it.width / it.height.toFloat()
                val item = Item(
                    it.imageId as ItemId,
                    it.name.orEmpty(),
                    Color.parseColor("#" + it.accentColor),
                    it.thumbnailUrl.orEmpty(),
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
                logger.log("${item.id}: ${item.imageRatio}")
            }
            list
        }
    }
}
