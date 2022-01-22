package com.gaelaelrasi.dogbreeds.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.databinding.FragmentSearchBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.FragmentsRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchViewFragment.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(breedNameInput: String?): Boolean {
                binding.searchViewFragment.clearFocus()
                if(!breedNameInput.isNullOrEmpty()){
                    getBreedByName(instanceViewModel(), breedNameInput)
                }
                return false
            }

            override fun onQueryTextChange(newTextInput: String?): Boolean {
                if(!newTextInput.isNullOrEmpty()){
                    getBreedByName(instanceViewModel(), newTextInput)
                }else{
                    TODO("Mostra alerta -> Inserir texto para pesquisa")
                }
                return false
            }
        })

        return binding.root
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedByName(viewModel: FragmentsViewModel, breedNameInput: String) {
        compositeDisposable.add(
            viewModel.getBreedsByName(breedNameInput)
                .subscribe(
                    { breedList ->
                        setUpRecyclerView(breedList)
                    },
                    { throwable ->
                        Log.e("SearchFragment", throwable.message ?: "onError")
                    }
                )
        )
    }

    private fun setUpRecyclerView(breedList: List<Breed>) {
        binding.recyclerViewSearchFragment.apply {
            adapter = FragmentsRecyclerViewAdapter(breedList)
            adapter = adapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }
}