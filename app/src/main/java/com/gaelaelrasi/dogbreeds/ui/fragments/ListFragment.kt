package com.gaelaelrasi.dogbreeds.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.databinding.FragmentListBinding
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.ui.adapter.ListFragmentRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.DefaultErrorHandler
import com.gaelaelrasi.dogbreeds.util.MainViewModelFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.PublishProcessor
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var defaultErrorHandler: DefaultErrorHandler

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: FragmentListBinding
    private var listAdapter: ListFragmentRecyclerViewAdapter? = null

    private var mPublishProcessor: PublishProcessor<Int?>? = null
    private var pageNumber = 0
    private val VISIBLE_THRESHOLD = 1
    private var lastVisibleItem: Int? = null
    private var totalItemCount: Int? = null
    private var loading = false
    var dataList : List<Breed> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        mPublishProcessor = PublishProcessor.create()

        setUpRecyclerView()
        getBreedsList(instanceViewModel())

        return binding.root
    }

    private fun instanceViewModel(): FragmentsViewModel {
        val factory = MainViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[FragmentsViewModel::class.java]
    }

    private fun getBreedsList(viewModel: FragmentsViewModel) {
        compositeDisposable.add(
            viewModel.getBreeds(pageNumber)
                .doOnNext {

                }
                .doOnError {
                    defaultErrorHandler.getMessage(it)
                }
                .map {
                    listAdapter!!.addAllItems(it)
                    binding.listFragmentRecyclerView.adapter!!.notifyDataSetChanged()
                }
                .subscribe(
                    { breedList ->

                    },
                    { throwable ->
                        Log.e("ListFragment", throwable.message ?: "onError")
                    }
                )
        )
    }


    private fun setUpRecyclerView() {
        binding.listFragmentRecyclerView.apply {
            listAdapter = ListFragmentRecyclerViewAdapter()
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun setUpLoadMoreListener() {
        binding.listFragmentRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount =  binding.listFragmentRecyclerView.layoutManager!!.itemCount
                lastVisibleItem = (binding.listFragmentRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loading
                    && totalItemCount!! <= lastVisibleItem!! + VISIBLE_THRESHOLD
                ) {
                    pageNumber++
                    mPublishProcessor!!.onNext(pageNumber)
                    loading = true
                }
            }
        })
        mPublishProcessor!!.onNext(pageNumber)
    }
}