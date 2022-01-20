package com.gaelaelrasi.dogbreeds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gaelaelrasi.dogbreeds.DefaultApplication
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.remote.BreedsApi
import com.gaelaelrasi.dogbreeds.databinding.ActivityMainBinding
import com.gaelaelrasi.dogbreeds.di.component.ActivityComponent
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import com.gaelaelrasi.dogbreeds.fragments.BreedsListFragment
import com.gaelaelrasi.dogbreeds.fragments.SearchBreedsFragment
import com.gaelaelrasi.dogbreeds.util.ViewModelFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var breedsApi: BreedsApi? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[ MainViewModel::class.java ]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    /*private fun instanceViewModel(): MainViewModel {
        val factory = ViewModelFactory(NetworkModule())
        return ViewModelProvider(this, factory)[MainViewModel::class.java]
    }*/

    private fun initView(){
        viewModel.coinListLiveData.observe(this){
            println("TODAYBREED: $it")
        }
    }

   /* fun initView(){
        val listFragment = BreedsListFragment()
        val searchFragment = SearchBreedsFragment()

        setCurrentFragment(listFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_item_list -> setCurrentFragment(listFragment)
                R.id.menu_item_search -> setCurrentFragment(searchFragment)
            }
            true
        }
    }*/

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}