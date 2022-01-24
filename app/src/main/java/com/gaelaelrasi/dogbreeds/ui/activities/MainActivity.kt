package com.gaelaelrasi.dogbreeds.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.databinding.ActivityMainBinding
import com.gaelaelrasi.dogbreeds.ui.fragments.ListFragment
import com.gaelaelrasi.dogbreeds.ui.fragments.SearchFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initView(){
       val listFragment = ListFragment()
       val searchFragment = SearchFragment()
       setCurrentFragment(listFragment)
       binding.bottomNavigation.setOnItemSelectedListener {
           when(it.itemId){
               R.id.menu_item_list -> setCurrentFragment(listFragment)
               R.id.menu_item_search -> setCurrentFragment(searchFragment)
           }
           true
       }
   }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}
