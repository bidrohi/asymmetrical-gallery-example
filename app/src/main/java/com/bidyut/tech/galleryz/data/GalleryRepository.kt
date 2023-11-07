package com.bidyut.tech.galleryz.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bidyut.tech.galleryz.R
import com.bidyut.tech.galleryz.model.Response
import com.squareup.moshi.Moshi
import me.tatarka.inject.annotations.Inject
import okio.buffer
import okio.source
import java.io.InputStream
import java.util.concurrent.Executors

@Inject
class GalleryRepository(
    private val moshi: Moshi,
) {
    fun getResponse(ctx: Context): LiveData<Response> {
        val data = MutableLiveData<Response>()
        Executors.newSingleThreadExecutor().submit {
            val jsonAdapter = moshi.adapter(Response::class.java)
            val inputStream: InputStream = ctx.resources.openRawResource(R.raw.data)
            data.postValue(jsonAdapter.fromJson(inputStream.source().buffer()))
        }
        return data
    }
}
