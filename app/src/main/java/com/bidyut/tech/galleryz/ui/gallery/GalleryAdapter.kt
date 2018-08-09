package com.bidyut.tech.galleryz.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bidyut.tech.galleryz.R
import com.bidyut.tech.galleryz.model.Item
import com.squareup.picasso.Picasso

class GalleryAdapter(ctx: Context) : RecyclerView.Adapter<ImageViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)
    private val picasso: Picasso = Picasso.get()
    private var items: List<Item> = emptyList()

    val spanSizeLookup: GridLayoutManager.SpanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return items[position].columns
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].columns
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(inflater.inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.label.text = item.name
        picasso.load(item.url)
                .fit()
                .centerCrop()
                .into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val label: TextView = itemView.findViewById(R.id.label)
}
