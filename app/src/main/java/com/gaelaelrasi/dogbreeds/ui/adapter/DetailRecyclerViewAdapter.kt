package com.gaelaelrasi.dogbreeds.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaelaelrasi.dogbreeds.R

class DetailRecyclerViewAdapter: RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val detailBreedResult: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_card_information, parent, false))
    }

    override fun getItemCount(): Int {
        return if (detailBreedResult.isEmpty()) {
            0
        } else detailBreedResult.size
    }

    private fun add(result: String) {
        detailBreedResult.add(result)
        notifyItemRangeInserted(itemCount,detailBreedResult.size - 1)
    }

    //Function called on DetailActivity to add all items on the recycler view
    fun addAllItems(detailResults: List<String>) {
        for (result in detailResults) {
            add(result)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            if(detailBreedResult[position].isEmpty() || detailBreedResult[position] == "null" ){
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
