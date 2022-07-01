package com.olvera.moviedb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.olvera.moviedb.R
import com.olvera.moviedb.util.BaseFragment
import com.olvera.moviedb.databinding.SearchFragmentBinding
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null && query.isNotEmpty()) { // Es para ver si el usuario escribio algo en el searchView
                    viewModel.search(query)
                    viewBinding.animationView.visibility = View.GONE
                    viewBinding.recyclerviewSearch.visibility = View.VISIBLE

                    return true
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean { // Es para cuando se escribe en el searchview y se cambia el texto
                return false
            }
        })

        viewBinding.searchView.setOnCloseListener { // Cuando se cierra la vista de búsqueda, queremos borrar los resultados de la búsqueda
            viewBinding.animationView.visibility = View.VISIBLE
            viewBinding.recyclerviewSearch.visibility = View.GONE
            true
        }

        viewModel.search.observe(viewLifecycleOwner) {
            recyclerviewSearch.layoutManager = LinearLayoutManager(context)
            recyclerviewSearch.adapter = SearchAdapter(it) {
                val bundle = bundleOf("movie_details" to it)
                Navigation.findNavController(view)
                    .navigate(R.id.next_fragment, bundle)
            }
        }
    }



    override fun getViewBinding(): Int {
        return R.layout.search_fragment
    }

    override fun getViewModel(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

}