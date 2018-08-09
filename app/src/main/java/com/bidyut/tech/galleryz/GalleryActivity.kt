package com.bidyut.tech.galleryz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bidyut.tech.galleryz.ui.gallery.GalleryFragment

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GalleryFragment.newInstance())
                    .commitNow()
        }
    }
}
