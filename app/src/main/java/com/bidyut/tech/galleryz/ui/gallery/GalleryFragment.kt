package com.bidyut.tech.galleryz.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bidyut.tech.galleryz.R
import com.bidyut.tech.galleryz.di.AppGraph

class GalleryFragment : Fragment() {
    private val viewModel: GalleryViewModel by viewModels {
        GalleryGraph::class.create(AppGraph.instance).ourViewModelFactory
    }
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
        val adapter = GalleryAdapter(requireContext()) {
            viewModel.selectedItemId = it.id
        }
        recyclerView?.adapter = adapter
        layoutManager.spanSizeLookup = adapter.spanSizeLookup

        viewModel.getItems(requireContext(), columns).observe(viewLifecycleOwner) { items ->
            adapter.setItems(items)
        }
    }
}
