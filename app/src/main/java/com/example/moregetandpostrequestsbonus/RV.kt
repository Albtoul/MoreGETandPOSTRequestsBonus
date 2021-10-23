package com.example.moregetandpostrequestsbonus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moregetandpostrequestsbonus.databinding.ItemRowBinding

class RV (val names: ArrayList<String>): RecyclerView.Adapter<RV.Holder>(){
    class Holder (val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        var name = names[position]
        holder.binding.apply {
            textView.text = name
        }
    }

    override fun getItemCount() = names.size

}