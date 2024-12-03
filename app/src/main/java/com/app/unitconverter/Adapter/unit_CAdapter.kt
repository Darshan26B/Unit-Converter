package com.app.unitconverter.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.unitconverter.Activity.CommonActivity
import com.app.unitconverter.Model.unitModel
import com.app.unitconverter.databinding.UnitItemviewBinding

class unit_CAdapter(private var list: ArrayList<unitModel>) :
    RecyclerView.Adapter<unit_CAdapter.unitHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class unitHolder(val binding: UnitItemviewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): unitHolder {
        val binding = UnitItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return unitHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: unitHolder, position: Int) {
        holder.binding.apply {
            with(list[position]) {
                unitCName.text = this.UnitName
                unitCImage.setImageResource(this.UnitImage)
            }
        }
    }
}
