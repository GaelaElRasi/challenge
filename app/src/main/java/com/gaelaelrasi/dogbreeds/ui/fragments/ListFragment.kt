package com.gaelaelrasi.dogbreeds.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.databinding.FragmentListBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.RecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        getBreedsList(instanceViewModel())

        binding.listFragmentRecyclerView.setOnClickListener {
            TODO("Go To Detail View")
        }

        return binding.root
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedsList(viewModel: FragmentsViewModel) {
        viewModel.getBreeds()
            .subscribe(
                { breedList ->
                    setUpRecyclerView(breedList)
                },
                { throwable ->
                    Log.e("ListFragment", throwable.message ?: "onError")
                }
            )
    }

    private fun setUpRecyclerView(breedList: List<Breed>) {
        binding.listFragmentRecyclerView.apply {
            adapter = RecyclerViewAdapter(breedList)
            adapter = adapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }
}