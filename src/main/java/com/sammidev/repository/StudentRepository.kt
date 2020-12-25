package com.sammidev.repository
import com.sammidev.entity.Student

interface StudentRepository {
    fun findStudentById(id: Int): Student?
    fun findStudentByNim(nim: String): Student?
    fun findStudentByName(name: String): Student?
    fun findStudents(): List<Student?>?
    fun createStudent(student: Student): Student?
    fun deleteStudentById(id: Int): String
    fun deleteStudentByName(name: String): String
    fun deleteStudentByNim(nim: String): String
    fun updateStudent(student: Student): Student?
    fun countStudents(): Int?
}