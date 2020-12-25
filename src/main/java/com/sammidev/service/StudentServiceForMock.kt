package com.sammidev.service

import com.sammidev.entity.Student
import com.sammidev.repository.StudentRepository

class StudentServiceForMock(val studentRepository: StudentRepository) {

    fun findStudentById(id: Int): Student? = studentRepository.findStudentById(id)
    fun findStudentByNim(nim: String): Student? = studentRepository.findStudentByNim(nim)
    fun findStudentByName(name: String): Student? = studentRepository.findStudentByName(name)
    fun findStudents(): List<Student?>? = studentRepository.findStudents()
    fun createStudent(student: Student): Student? = studentRepository.createStudent(student)
    fun deleteStudentById(id: Int): String = studentRepository.deleteStudentById(id)
    fun deleteStudentByName(name:String): String = studentRepository.deleteStudentByName(name)
    fun deleteStudentByNim(nim: String): String = studentRepository.deleteStudentByNim(nim)
    fun updateStudent(id: Int, student: Student): Student? = studentRepository.updateStudent(student)
    fun countStudents(): Int? = studentRepository.countStudents()
}