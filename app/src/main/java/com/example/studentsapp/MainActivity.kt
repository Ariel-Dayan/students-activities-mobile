package com.example.studentsapp

import android.annotation.SuppressLint
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.OnItemClickListener
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class MainActivity : AppCompatActivity() {
    private var studentsRecyclerView: RecyclerView? = null
    private var students: MutableList<Student>? = null
    private var addStudentButton: Button? = null
    private var noStudentsTextView: TextView? = null
    private var adapter: StudentsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = Model.instance.students
        setStudentsRecyclerView()

        noStudentsTextView = findViewById(R.id.tvMainNoStudentsMessage)
        addStudentButton = findViewById(R.id.btnMainAddStudent)
        addStudentButton?.setOnClickListener{ onAddStudentButtonClick() }

        toggleNoStudentsMessage()
    }

    private fun toggleNoStudentsMessage() {
        if (students == null || students?.isEmpty() == true) {
            noStudentsTextView?.visibility = TextView.VISIBLE
        } else {
            noStudentsTextView?.visibility = TextView.GONE
        }
    }

    private fun setStudentsRecyclerView() {
        studentsRecyclerView = findViewById(R.id.rvMainStudentsList)
        studentsRecyclerView?.setHasFixedSize(true)

        adapter = StudentsRecyclerAdapter(students)
        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(student: Student?) {
         
            }
        }

        studentsRecyclerView?.layoutManager = LinearLayoutManager(this)
        studentsRecyclerView?.adapter = adapter

        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        studentsRecyclerView?.addItemDecoration(divider)

    }

    private fun onAddStudentButtonClick() {
   
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
        toggleNoStudentsMessage()
    }
}