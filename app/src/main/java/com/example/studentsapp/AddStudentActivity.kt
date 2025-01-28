package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class AddStudentActivity : AppCompatActivity() {
    private var saveNewStudentButton: Button? = null
    private var cancelNewStudentButton: Button? = null
    private var studentNameEditText: EditText? = null
    private var studentIdEditText: EditText? = null
    private var studentPhoneEditText: EditText? = null
    private var studentAddressEditText: EditText? = null
    private var studentCheckStatusCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        saveNewStudentButton = findViewById(R.id.btnAddStudentSave)
        cancelNewStudentButton = findViewById(R.id.btnAddStudentCancel)
        studentNameEditText = findViewById(R.id.etAddStudentName)
        studentIdEditText = findViewById(R.id.etAddStudentId)
        studentPhoneEditText = findViewById(R.id.etAddStudentPhone)
        studentAddressEditText = findViewById(R.id.etAddStudentAddress)
        studentCheckStatusCheckBox = findViewById(R.id.cbAddStudentCheckStatusTag)

        saveNewStudentButton?.setOnClickListener { onSaveNewStudentButtonClick() }
        cancelNewStudentButton?.setOnClickListener { onCancelNewStudentButtonClick() }
    }


    private fun onSaveNewStudentButtonClick() {
        val name = studentNameEditText?.text.toString()
        val id = studentIdEditText?.text.toString().toIntOrNull()
        val phone = studentPhoneEditText?.text.toString()
        val address = studentAddressEditText?.text.toString()
        val isChecked = studentCheckStatusCheckBox?.isChecked ?: false


        val isStudentIdAlreadyExist = Model.instance.students.any { it.id == id }

        if (isStudentIdAlreadyExist) {
            Toast.makeText(applicationContext, "Student ID already exist", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isNotEmpty() && id != null && phone.isNotEmpty() && address.isNotEmpty()) {
            Model.instance.addStudent(Student(id, name, phone, address, isChecked))
            Toast.makeText(this, "Student saved", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCancelNewStudentButtonClick() {
        finish()
    }
}