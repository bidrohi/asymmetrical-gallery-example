package com.bidyut.tech.galleryz.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bidyut.tech.galleryz.R

class GalleryFragment : Fragment() {
    private val columns = 15

    companion object {
        fun newInstance() = GalleryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(context, columns)
        recyclerView?.layoutManager = layoutManager
        val adapter = GalleryAdapter(requireContext())
        recyclerView?.adapter = adapter
        layoutManager.spanSizeLookup = adapter.spanSizeLookup

        val viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        viewModel.getItems(requireContext(), columns).observe(viewLifecycleOwner, { items ->
            adapter.setItems(items)
        })
    }
}
