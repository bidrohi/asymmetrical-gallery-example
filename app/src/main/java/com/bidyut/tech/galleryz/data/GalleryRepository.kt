package com.bidyut.tech.galleryz.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bidyut.tech.galleryz.R
import com.bidyut.tech.galleryz.model.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Okio
import java.io.InputStream
import java.util.concurrent.Executors

class GalleryRepository {
    fun getResponse(ctx: Context): LiveData<Response> {
        val data = MutableLiveData<Response>()
        Executors.newSingleThreadExecutor().submit {
            val jsonAdapter = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                    .adapter<Response>(Response::class.java)
            val inputStream: InputStream = ctx.resources.openRawResource(R.raw.data)
            data.postValue(jsonAdapter.fromJson(Okio.buffer(Okio.source(inputStream))))
        }
        return data
    }
}
