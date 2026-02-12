package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.R
import com.example.hseapp.databinding.PeriodlistBinding
import com.example.hseapp.datamodels.Period
import com.example.hseapp.interfaces.ItemClickListener
import com.example.hseapp.ui.fragments.GradesFragment
import com.google.android.material.card.MaterialCardView

class PeriodAdapter(var names:ArrayList<Period>?, val obj: GradesFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PeriodlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeriodHolder(binding, obj)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PeriodHolder).bind(names!![position])

    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class PeriodHolder(val binding: PeriodlistBinding, val obj: ItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(period: Period) {
            binding.period = period
            var cardView : MaterialCardView =  binding.root as MaterialCardView

            if(period.isSelected) {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.courses_hse_blue))
                binding.subject.setTextColor(binding.root.context.getColor(R.color.primary_hse_blue))
            }

            else {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.white))
                binding.subject.setTextColor(binding.root.context.getColor(R.color.not_selected))
            }
            binding.root.setOnClickListener {
                obj.onPeriodClick(period)
            }
        }
    }
}