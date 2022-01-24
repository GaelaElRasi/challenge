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

class SearchFragmentRecyclerViewAdapter: RecyclerView.Adapter<SearchFragmentRecyclerViewAdapter.ViewHolder>() {

    private val resultRequest: MutableList<Breed> = mutableListOf()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.card_information, parent, false))
    }

    override fun getItemCount(): Int {
        return resultRequest.size
    }

    private fun add(result: Breed) {
        resultRequest.add(result)
        notifyItemInserted(resultRequest.size - 1)
    }

    //Function called on SearchFragment to add all items on the recycler view
    fun addAllItems(breedResults: List<Breed>) {
        for (result in breedResults) {
            add(result)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       with(holder){
           breedGroup.visibility = View.VISIBLE
           breedOrigin.visibility = View.VISIBLE
           breedTemperament.visibility = View.VISIBLE

           breedName.text = resultRequest[position].name
           breedGroup.text = resultRequest[position].breedGroup
           breedOrigin.text = resultRequest[position].origin
           breedTemperament.text = resultRequest[position].temperament
           Picasso.get().load(resultRequest[position].image.url).into(breedImage)
           breedLayout.setOnClickListener { goToBreedDetailView( resultRequest[position]) }
       }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var breedName: TextView = itemView.findViewById(R.id.card_info_breed_name)
        var breedGroup: TextView = itemView.findViewById(R.id.card_info_breed_group)
        var breedOrigin: TextView = itemView.findViewById(R.id.card_info_breed_origin)
        var breedTemperament: TextView = itemView.findViewById(R.id.card_info_breed_temperament)
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
}