package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {
    private var editStudentButton: Button? = null
    private var studentNameTextView: TextView? = null
    private var studentIdTextView: TextView? = null
    private var studentPhoneTextView: TextView? = null
    private var studentAddressTextView: TextView? = null
    private var studentCheckStatusCheckBox: CheckBox? = null
    private var student: Student? = null
    private var studentProfileImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        editStudentButton = findViewById(R.id.btnStudentDetailsEdit)
        studentNameTextView = findViewById(R.id.tvStudentDetailsName)
        studentIdTextView = findViewById(R.id.tvStudentDetailsId)
        studentPhoneTextView = findViewById(R.id.tvStudentDetailsPhone)
        studentAddressTextView = findViewById(R.id.tvStudentDetailsAddress)
        studentCheckStatusCheckBox = findViewById(R.id.cbStudentDetailsCheckStatusTag)
        studentProfileImageView = findViewById(R.id.ivStudentDetailsProfileImage)

        editStudentButton?.setOnClickListener{ onEditStudentButtonClick() }

        initData()
    }

    private fun onEditStudentButtonClick() {
        val intent = Intent(this, EditStudentActivity::class.java)

        intent.putExtra(Model.instance.studentIdParam, student?.id)
        startActivity(intent)
        finish()
    }

    private fun initData() {
        val studentId = intent.getIntExtra(Model.instance.studentIdParam, Model.instance.students[0].id)
        student = Model.instance.getStudentById(studentId)

        studentProfileImageView?.setImageResource(student?.picture ?: R.drawable.avatar)
        studentNameTextView?.text = student?.name
        studentIdTextView?.text = student?.id.toString()
        studentPhoneTextView?.text = student?.phone
        studentAddressTextView?.text = student?.address
        studentCheckStatusCheckBox?.isChecked = student?.isChecked ?: false
    }
}