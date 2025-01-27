package com.example.studentsapp.model

import com.example.studentsapp.R

data class Student(
    var id: Int,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false,
    val picture: Int = R.drawable.avatar
)