package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {
    private var saveEditStudentButton: Button? = null
    private var cancelEditStudentButton: Button? = null
    private var deleteEditStudentButton: Button? = null
    private var studentNameEditText: EditText? = null
    private var studentIdEditText: EditText? = null
    private var studentPhoneEditText: EditText? = null
    private var studentAddressEditText: EditText? = null
    private var studentCheckStatusCheckBox: CheckBox? = null
    private var student: Student? = null
    private var studentProfileImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        saveEditStudentButton = findViewById(R.id.btnEditStudentSave)
        cancelEditStudentButton = findViewById(R.id.btnEditStudentCancel)
        deleteEditStudentButton = findViewById(R.id.btnEditStudentDelete)
        studentNameEditText = findViewById(R.id.etEditStudentName)
        studentIdEditText = findViewById(R.id.etEditStudentId)
        studentPhoneEditText = findViewById(R.id.etEditStudentPhone)
        studentAddressEditText = findViewById(R.id.etEditStudentAddress)
        studentCheckStatusCheckBox = findViewById(R.id.cbEditStudentCheckStatusTag)
        studentProfileImageView = findViewById(R.id.ivEditStudentProfileImage)

        saveEditStudentButton?.setOnClickListener { onSaveEditStudentButtonClick() }
        cancelEditStudentButton?.setOnClickListener { onCancelEditStudentButtonClick() }
        deleteEditStudentButton?.setOnClickListener { onDeleteEditStudentButtonClick() }

        initData()
    }

    private fun initData() {
        val studentId = intent.getIntExtra(Model.instance.studentIdParam, Model.instance.students[0].id)
        student = Model.instance.getStudentById(studentId)

        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        studentProfileImageView?.setImageResource(student?.picture ?: R.drawable.avatar)
        studentNameEditText?.setText(student?.name)
        studentIdEditText?.setText(student?.id.toString())
        studentPhoneEditText?.setText(student?.phone)
        studentAddressEditText?.setText(student?.address)
        studentCheckStatusCheckBox?.isChecked = student?.isChecked ?: false
    }

    private fun onSaveEditStudentButtonClick() {
        val name = studentNameEditText?.text.toString()
        val id = studentIdEditText?.text.toString().toIntOrNull()
        val phone = studentPhoneEditText?.text.toString()
        val address = studentAddressEditText?.text.toString()
        val isChecked = studentCheckStatusCheckBox?.isChecked ?: false

        val isStudentIdAlreadyExist = Model.instance.students.any { it.id != student?.id && it.id == id }

        if (isStudentIdAlreadyExist) {
            Toast.makeText(applicationContext, "Student ID already exist", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isNotEmpty() && id != null && phone.isNotEmpty() && address.isNotEmpty()) {
            Model.instance.updateStudent(student?.id, Student(id, name, phone, address, isChecked))
            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCancelEditStudentButtonClick() {
        finish()
    }

    private fun onDeleteEditStudentButtonClick() {
        Model.instance.deleteStudent(student?.id)
        Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
        finish()
    }
}