package com.gaelaelrasi.dogbreeds.ui.adapter

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
    private val resultRequest: List<Breed>
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
       }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var breedName: TextView = itemView.findViewById(R.id.card_info_breed_name)
        var breedGroup: TextView = itemView.findViewById(R.id.card_info_breed_group)
        var breedOrigin: TextView = itemView.findViewById(R.id.card_info_breed_origin)
        var breedTemperament: TextView = itemView.findViewById(R.id.card_info_breed_temperament)
        var breedImage: ImageView = itemView.findViewById(R.id.card_info_image_view)
    }

}