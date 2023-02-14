package com.nyc.schools.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nyc.schools.model.School
import com.nyc.schools.databinding.SchoolListItemBinding

class SchoolsListAdapter(private val schoolCardClickListener: SchoolCardClickListener) :
    RecyclerView.Adapter<SchoolsListAdapter.SchoolViewHolder>() {
    private val schoolsList: MutableList<School> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding = SchoolListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SchoolViewHolder(
            binding = binding
        )
    }

    override fun getItemCount(): Int = schoolsList.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school: School = schoolsList[position]
        holder.bind(school)
        holder.binding.apply {
            cardView.setOnClickListener {
                schoolCardClickListener.onClick(school)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSchoolsList(schoolsList: List<School>?) {
        schoolsList?.let {
            this.schoolsList.clear()
            this.schoolsList.addAll(schoolsList)
            this.notifyDataSetChanged()
        }
    }

    interface SchoolCardClickListener {
        fun onClick(school: School)
    }

    inner class SchoolViewHolder(val binding: SchoolListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            binding.schoolName.text = school.school_name
            binding.city.text = school.city
            binding.phoneNumber.text = school.phone_number
        }
    }
}