package com.maverick.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maverick.studentapp.databinding.MotorcycleListItemBinding
import com.maverick.studentapp.model.Motorcycle

class MotorcycleListAdapter(val motorcycleList:ArrayList<Motorcycle>):RecyclerView.Adapter<MotorcycleListAdapter.MotorcycleViewHolder>() {
    class MotorcycleViewHolder(var binding:MotorcycleListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorcycleViewHolder {
        val binding = MotorcycleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MotorcycleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return motorcycleList.size
    }

    override fun onBindViewHolder(holder: MotorcycleViewHolder, position: Int) {
        holder.binding.txtNameMotorcycle.text = motorcycleList[position].name
        holder.binding.txtManufacturerMotorcycle.text = motorcycleList[position].manufacturer
        holder.binding.txtYearMotorcycle.text = motorcycleList[position].year.toString()
        var colors:String = ""
        for (color in motorcycleList[position].colors!!){
            colors += color + " "
        }
        holder.binding.txtColorMotorcycle.text = colors
        var engineMotorcycle = motorcycleList[position].engine
        if (engineMotorcycle != null) {
            holder.binding.txtTypeEngine.text = engineMotorcycle.type
            holder.binding.txtDisplacementEngine.text = engineMotorcycle.displacement.toString()
            holder.binding.txtPowerEngine.text = engineMotorcycle.power
        }
    }

    fun updateMotorcycleList(newMotorcycleList: ArrayList<Motorcycle>){
        motorcycleList.clear()
        motorcycleList.addAll(newMotorcycleList)
        notifyDataSetChanged()
    }
}