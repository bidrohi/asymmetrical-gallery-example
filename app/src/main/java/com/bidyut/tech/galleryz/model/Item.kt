package com.bidyut.tech.galleryz.model

import com.squareup.moshi.JsonClass

typealias ItemId = String

@JsonClass(generateAdapter = true)
data class Item(
        val id: ItemId,
        val name: String,
        val accentColor: Int,
        val url: String,
        val imageRatio: Float,
        var columns: Int = 0
)
