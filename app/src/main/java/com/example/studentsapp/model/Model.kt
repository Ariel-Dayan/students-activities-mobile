package com.example.studentsapp.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val instance: Model = Model()
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(id: Int? ,updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == id }

        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    fun getStudentById(id: Int?): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudent(id: Int?) {
        val index = students.indexOfFirst { it.id == id }

        if (index != -1) {
            students.removeAt(index)
        }
    }
}