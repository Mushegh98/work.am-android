package com.musheg_h.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.Repository
import com.musheg_h.myapplication.adapter.WorkAdapter
import com.musheg_h.myapplication.models.Work
import kotlinx.android.synthetic.main.activity_newscreen.*

class NewScreenActivity : AppCompatActivity() {


    private var adapter : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>? = null
    private var layoutManager : RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newscreen)

        val arrayList : ArrayList<Work> = ArrayList()
        arrayList.add(Work(R.drawable.picsart,"Junior Android Developer","PicsArt","20 May 2020","Երևան"))
        arrayList.add(Work(R.drawable.bet,"Back-End Developer","BetConstruct","22 May 2020","Երևան"))
        arrayList.add(Work(R.drawable.omd,"C++ Developer","OMD Armenia","21 May 2020","Երևան"))
        arrayList.add(Work(R.drawable.zero,".NET Developer","ZERØ","19 May 2020","Երևան"))
        arrayList.add(Work(R.drawable.digitain,".NET Developer","Digitain","20 May 2020","Երևան"))
        arrayList.add(Work(R.drawable.help,"Senior QA Engineer","HelpSystems","22 May 2020","Երևան"))

        recyclerView.setHasFixedSize(true)
        adapter = WorkAdapter(arrayList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager


        val token = intent.getStringExtra("token")

//        var response : String
//        button.setOnClickListener {
//           Repository.postHelloUser(token!!).observe(this as LifecycleOwner , Observer {
//               response = it.token
//               text.text = response
//           })
//        }

    }

}