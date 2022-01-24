package com.gaelaelrasi.dogbreeds.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gaelaelrasi.dogbreeds.databinding.FragmentSearchBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.SearchFragmentRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.DefaultErrorHandler
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var defaultErrorHandler: DefaultErrorHandler

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: FragmentSearchBinding
    private var searchAdapter: SearchFragmentRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setUpSearchView()

        return binding.root
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedByName(viewModel: FragmentsViewModel, breedNameInput: String) {
        setUpRecyclerView()
        compositeDisposable.add(
            viewModel.getBreedsByName(breedNameInput)
                .doOnError {
                    defaultErrorHandler.getMessage(it)
                }
                .map {
                    searchAdapter!!.addAllItems(it)
                    binding.recyclerViewSearchFragment.adapter!!.notifyDataSetChanged()
                }
                .subscribe()
        )
    }

    private fun getImageBreedsById(viewModel: FragmentsViewModel, breedList: Unit) {
       /* compositeDisposable.add(
            viewModel.getBreedsImageById(breedList)
                .doAfterTerminate{  }
                .doOnError {
                    defaultErrorHandler.getMessage(it)
                }
                .subscribe(
                    { breedList ->
                        setUpRecyclerView(breedList)
                    },
                    { throwable ->
                        Log.e("ListFragment", throwable.message ?: "onError")
                    }
                )
        )*/
    }

    private fun setUpSearchView() {
        binding.searchViewFragment.setOnCloseListener {
            binding.recyclerViewSearchFragment.adapter!!.notifyDataSetChanged()
            false
        }

        binding.searchViewFragment.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(breedNameInput: String?): Boolean {
                binding.searchViewFragment.clearFocus()
                if(!breedNameInput.isNullOrEmpty()){
                    getBreedByName(instanceViewModel(), breedNameInput)
                }
                return false
            }

            override fun onQueryTextChange(newTextInput: String): Boolean {
                getBreedByName(instanceViewModel(), newTextInput)
                return false
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewSearchFragment.apply {
            searchAdapter = SearchFragmentRecyclerViewAdapter()
            adapter = searchAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }
}