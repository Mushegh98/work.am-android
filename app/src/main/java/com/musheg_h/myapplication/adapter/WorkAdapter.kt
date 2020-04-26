package com.musheg_h.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.Work

class WorkAdapter(private var arrayList: ArrayList<Work>) : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

     class WorkViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
     {
         var imageWork : ImageView = itemView.findViewById<ImageView>(R.id.workImageView)
         var position : TextView = itemView.findViewById(R.id.positionTextView)
         var companyName : TextView = itemView.findViewById(R.id.companyTextView)
         var deadLine : TextView = itemView.findViewById(R.id.deadLineTextView)
         var locationCompany : TextView = itemView.findViewById(R.id.locationTextView)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.work_item,parent,false)
        return WorkViewHolder(view)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work : Work = arrayList[position]
        holder.imageWork.setImageResource(work.imageWork)
        holder.position.text = work.position
        holder.companyName.text = work.companyName
        holder.deadLine.text = work.deadLine
        holder.locationCompany.text = work.locationCompany
    }

}