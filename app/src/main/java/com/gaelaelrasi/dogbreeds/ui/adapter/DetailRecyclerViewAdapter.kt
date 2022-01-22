package com.gaelaelrasi.dogbreeds.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.R

class DetailRecyclerViewAdapter(
    private val detailBreedResult: List<String>
): RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_card_information, parent, false))
    }

    override fun getItemCount(): Int {
        return detailBreedResult.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            if(detailBreedResult[position].isEmpty()){
                textDetail.text = context.getString(R.string.error_without_information)
            }else{
                textDetail.text = detailBreedResult[position]
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textDetail: TextView = itemView.findViewById(R.id.detail_card_information_text)
    }
}