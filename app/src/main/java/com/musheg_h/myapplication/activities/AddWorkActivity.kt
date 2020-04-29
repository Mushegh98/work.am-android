package com.musheg_h.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.musheg_h.myapplication.R
import com.musheg_h.myapplication.models.PostDTO
import com.musheg_h.myapplication.utils.Level
import com.musheg_h.myapplication.viewmodel.WorkAddViewModel
import kotlinx.android.synthetic.main.activity_add__work_.*

class AddWorkActivity : AppCompatActivity() {

   var workAddViewModel : WorkAddViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__work_)

        val intent : Intent = intent
        val token = intent.getStringExtra("token")

        button_addwork.setOnClickListener {
            workAddViewModel = WorkAddViewModel(application)
            val title = title_text.text.toString()
            val content = jobDescriptionText.text.toString()
            var position : Level = Level.JUNIOR
            when(position_group.checkedRadioButtonId)
            {
                R.id.id_junior-> position = Level.JUNIOR
                R.id.id_middle-> position = Level.MIDDLE
                R.id.id_senior-> position = Level.SENIOR
            }
            val postDTO : PostDTO = PostDTO(title,content,position)
            workAddViewModel?.postAddWork(postDTO,token)
            finish()
        }
    }
}
