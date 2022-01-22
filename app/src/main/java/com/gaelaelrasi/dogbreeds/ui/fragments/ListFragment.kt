package com.gaelaelrasi.dogbreeds.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.databinding.FragmentListBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.FragmentsRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.DefaultErrorHandler
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var defaultErrorHandler: DefaultErrorHandler

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        getBreedsList(instanceViewModel())

        return binding.root
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedsList(viewModel: FragmentsViewModel) {
        compositeDisposable.add(
            viewModel.getBreeds()
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
        )
    }

    private fun getImageBreedsById(viewModel: FragmentsViewModel, breedList: List<Breed>) {
        /*compositeDisposable.add(
            viewModel.getBreedsImageById(breedList.)
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

    private fun setUpRecyclerView(breedList: List<Breed>) {
        binding.listFragmentRecyclerView.apply {
            adapter = FragmentsRecyclerViewAdapter(breedList)
            adapter = adapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }
}