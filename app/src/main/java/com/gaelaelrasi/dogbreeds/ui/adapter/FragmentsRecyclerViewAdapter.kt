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

class FragmentsRecyclerViewAdapter(
    private val resultRequest: List<Breed>
): RecyclerView.Adapter<FragmentsRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.card_information, parent, false))
    }

    override fun getItemCount(): Int {
        return resultRequest.size
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