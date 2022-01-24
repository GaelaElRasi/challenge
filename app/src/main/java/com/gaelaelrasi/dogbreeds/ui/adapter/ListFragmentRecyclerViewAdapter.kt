package com.gaelaelrasi.dogbreeds.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.ui.activities.DetailActivity
import com.gaelaelrasi.dogbreeds.util.Constants
import com.squareup.picasso.Picasso

class ListFragmentRecyclerViewAdapter : RecyclerView.Adapter<ListFragmentRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val resultRequest: MutableList<Breed> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.card_information, parent, false))
    }

    override fun getItemCount(): Int {
        return if (resultRequest.isEmpty()) {
            0
        } else resultRequest.size
    }

    private fun add(result: Breed) {
        resultRequest.add(result)
        notifyItemInserted(resultRequest.size - 1)
    }

    //Function called on ListFragment to add all items on the recycler view
    fun addAllItems(breedResults: List<Breed>) {
        for (result in breedResults) {
            add(result)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       with(holder){
           breedName.text = resultRequest[position].name
           Picasso.get().load(resultRequest[position].image.url).into(breedImage)
           breedLayout.setOnClickListener { goToBreedDetailView( resultRequest[position]) }
       }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var breedName: TextView = itemView.findViewById(R.id.card_info_breed_name)
        var breedImage: ImageView = itemView.findViewById(R.id.card_info_image_view)
        var breedLayout: ConstraintLayout = itemView.findViewById(R.id.card_information_layout)
    }

    private fun goToBreedDetailView(breedResult: Breed) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_BREED_NAME, breedResult.name )
        intent.putExtra(Constants.EXTRA_BREED_ORIGIN, breedResult.origin )
        intent.putExtra(Constants.EXTRA_BREED_TEMPERAMENT, breedResult.temperament )
        context.startActivity(intent)
    }

    fun getLastVisibleItemId(): Int? {
        return if (resultRequest.isEmpty()) {
            0
        } else resultRequest[resultRequest.size - 1].id
    }
}