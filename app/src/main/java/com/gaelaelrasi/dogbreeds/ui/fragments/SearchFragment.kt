package com.gaelaelrasi.dogbreeds.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.databinding.FragmentListBinding
import com.gaelaelrasi.dogbreeds.databinding.FragmentSearchBinding
import dagger.android.support.DaggerFragment

class SearchFragment : Fragment() {

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
                    TODO("Call endpoint para fazer pesquisa -> Mostrar dados recyclerView")
                }
                return false
            }

            override fun onQueryTextChange(newTextInput: String?): Boolean {
                if(!newTextInput.isNullOrEmpty()){
                    TODO("Volta a call endpoint para pesquisa")
                }else{
                    TODO("Mostra alerta -> Inserir texto para pesquisa")
                }
                return false
            }
        })

        return binding.root
    }

    fun searchByBreedName(){

    }
}