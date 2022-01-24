package com.gaelaelrasi.dogbreeds.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.databinding.ActivityDetailBinding
import com.gaelaelrasi.dogbreeds.ui.adapter.DetailRecyclerViewAdapter
import com.gaelaelrasi.dogbreeds.util.Constants

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailBreedList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent!= null) {
            if(intent.hasExtra(Constants.EXTRA_BREED_NAME)){
                detailBreedList.add(intent.getStringExtra(Constants.EXTRA_BREED_NAME).toString())
            }
            if(intent.hasExtra(Constants.EXTRA_BREED_ORIGIN)){
                detailBreedList.add(intent.getStringExtra(Constants.EXTRA_BREED_ORIGIN).toString())
            }
            if(intent.hasExtra(Constants.EXTRA_BREED_TEMPERAMENT)){
                detailBreedList.add(intent.getStringExtra(Constants.EXTRA_BREED_TEMPERAMENT).toString())
            }
        }

       initView()
    }

    private fun initView() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.detailBreedList.apply {
            adapter = DetailRecyclerViewAdapter(detailBreedList)
            adapter = adapter
            layoutManager = LinearLayoutManager(
                this@DetailActivity,
                RecyclerView.VERTICAL,
                false)
        }
    }
}
