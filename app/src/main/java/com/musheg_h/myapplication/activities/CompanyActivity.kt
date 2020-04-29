package com.musheg_h.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.adapter.WorkAdapter
import com.musheg_h.myapplication.models.PageDTO
import com.musheg_h.myapplication.models.User
import com.musheg_h.myapplication.models.Work
import com.musheg_h.myapplication.utils.UserType
import com.musheg_h.myapplication.viewmodel.ListCompanyViewModel
import kotlinx.android.synthetic.main.activity_company.*

class CompanyActivity : AppCompatActivity() {

    var token : String = ""
    var listCompanyViewModel : ListCompanyViewModel? = null
    var page : Int =1
    private var adapter : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>? = null
    private var layoutManager : RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        val intent: Intent = intent
         token = intent.getStringExtra("token")
        var userType: UserType = intent.getSerializableExtra("userType") as UserType
        var userName: String = intent.getStringExtra("userName")



        listCompanyViewModel = ListCompanyViewModel(application)
        listCompanyViewModel?.postListCompany(token, PageDTO(page,3))?.observe(this, Observer {
            recyclerView.setHasFixedSize(true)
            adapter = WorkAdapter(it)
            layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
        })


    refresh.setOnRefreshListener {
        listCompanyViewModel?.postListCompany(token, PageDTO(page,3))?.observe(this, Observer {
            recyclerView.setHasFixedSize(true)
            adapter = WorkAdapter(it)
            layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            refresh.isRefreshing = false
        })
    }



        left_page.setOnClickListener{
            if(page>1)
            {
                page--
                listCompanyViewModel?.postListCompany(token, PageDTO(page,3))?.observe(this, Observer {
                    recyclerView.setHasFixedSize(true)
                    adapter = WorkAdapter(it)
                    layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = layoutManager
                })
            }
            else
            {
                return@setOnClickListener
            }
        }

        right_page.setOnClickListener {
            page++
            listCompanyViewModel?.postListCompany(token,PageDTO(page,3))?.observe(this, Observer {
                if(it.isEmpty())
                {
                    page--
                    return@Observer
                }
                else{
                    recyclerView.setHasFixedSize(true)
                    adapter = WorkAdapter(it)
                    layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = layoutManager
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.company_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_work -> {
                val intent = Intent(this, AddWorkActivity::class.java)
                intent.putExtra("token",token)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
