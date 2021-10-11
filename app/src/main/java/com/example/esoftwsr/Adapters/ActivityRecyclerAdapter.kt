package com.example.esoftwsr.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esoftwsr.Activity.Activity
import com.example.esoftwsr.databinding.ConstructorobjectBinding
import java.text.SimpleDateFormat
import java.util.*

class ActivityRecyclerAdapter(private var list: MutableList<Activity>):
    RecyclerView.Adapter<ActivityRecyclerAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ConstructorobjectBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val activity= list.elementAt(position)
        var format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        holder.binding.information.text ="${activity.date} | ${activity.lasting} | ${activity.activityType} | ${activity.comment}."
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ConstructorobjectBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

}