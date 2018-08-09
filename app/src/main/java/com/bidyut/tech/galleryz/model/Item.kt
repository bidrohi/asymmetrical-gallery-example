package com.bidyut.tech.galleryz.model

typealias ItemId = String

data class Item(
        val id: ItemId,
        val name: String,
        val accentColor: Int,
        val url: String,
        val imageRatio: Float
)
