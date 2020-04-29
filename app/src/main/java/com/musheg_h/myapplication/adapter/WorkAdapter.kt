package com.musheg_h.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.DAOPost
import java.text.SimpleDateFormat
import java.util.*

class WorkAdapter(private var arrayList: List<DAOPost>) :
    RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    class WorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var imageWork : ImageView = itemView.findViewById<ImageView>(R.id.workImageView)
        var position: TextView = itemView.findViewById(R.id.positionTextView)
        var content: TextView = itemView.findViewById(R.id.companyTextView)
        var date: TextView = itemView.findViewById(R.id.deadLineTextView)
        var companyName: TextView = itemView.findViewById(R.id.company_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.work_item, parent, false)
        return WorkViewHolder(view)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        val work: DAOPost = arrayList[position]
        // holder.imageWork.setImageResource(work.imageWork)
        val level: com.musheg_h.myapplication.utils.Level = work.level
        var positionWork: String = ""
        positionWork = when (level) {
            com.musheg_h.myapplication.utils.Level.JUNIOR -> "Junior"
            com.musheg_h.myapplication.utils.Level.MIDDLE -> "Middle"
            com.musheg_h.myapplication.utils.Level.SENIOR -> "Senior"
        }
        holder.position.text = positionWork + " " + work.title
        holder.content.text = work.content

        val pattern: String = "yyyy-MM-dd";
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern)

        val date: String = simpleDateFormat.format(work.date)

        holder.date.text = date
        holder.companyName.text = work.companyName
    }


}