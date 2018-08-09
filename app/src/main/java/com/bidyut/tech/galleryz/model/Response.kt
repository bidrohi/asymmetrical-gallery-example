package com.bidyut.tech.galleryz.model

data class Response(
        val webSearchUrl: String?,
        val value: List<Result>?
)

data class Size(
        val width: Int?,
        val height: Int?
)

data class Result(
        val imageId: String?,
        val name: String?,
        val datePublished: String?,
        val accentColor: String?,
        val width: Int?,
        val height: Int?,
        val contentUrl: String?,
        val thumbnail: Size?,
        val thumbnailUrl: String?
)
