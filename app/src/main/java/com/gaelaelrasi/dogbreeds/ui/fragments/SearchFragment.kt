package com.gaelaelrasi.dogbreeds.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.databinding.FragmentSearchBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.SearchFragmentRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.DefaultErrorHandler
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class SearchFragment : AppCompatDialogFragment() {

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

        initView()

        return binding.root
    }

    private fun initView() {
        setUpSearchView()
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedByName(viewModel: FragmentsViewModel, breedNameInput: String) {
        compositeDisposable.add(
            viewModel.getBreedsByName(breedNameInput)
                .doOnError { showAlertDialog(defaultErrorHandler.getMessage(it)) }
                .map {
                    searchAdapter!!.addAllItems(it)

                    binding.recyclerViewSearchFragment.adapter!!.notifyDataSetChanged()
                }
                .subscribe()
        )
        setUpRecyclerView()
    }

    private fun getImageBreedsById(viewModel: FragmentsViewModel, image_id: String) {
        compositeDisposable.add(
            viewModel.getBreedsImageById(image_id)
                .doAfterTerminate{  }
                .doOnError { showAlertDialog(defaultErrorHandler.getMessage(it)) }
                .map {}
                .subscribe()
        )
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

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.unknown_error))
            .setMessage(message)
            .setPositiveButton(R.string.unknown_error) { _, _ ->
                dismiss()
            }
            .show()
    }
}
