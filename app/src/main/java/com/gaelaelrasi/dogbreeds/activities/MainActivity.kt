package com.gaelaelrasi.dogbreeds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.databinding.ActivityMainBinding
import com.gaelaelrasi.dogbreeds.fragments.BreedsListFragment
import com.gaelaelrasi.dogbreeds.fragments.SearchBreedsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}