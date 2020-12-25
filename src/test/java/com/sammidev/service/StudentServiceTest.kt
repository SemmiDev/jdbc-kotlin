package com.sammidev.service

import com.sammidev.connection.ConnectionToDatabase
import com.sammidev.entity.Student
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest {

    var studentService: StudentService? = null

    @BeforeAll
    fun connectToPostgres() {
        val connection = ConnectionToDatabase().createConnectionToPostgres()
        studentService = connection?.let { StudentService(it) }
    }

    @Test
    fun testFindStudents() {
        val students =  studentService?.findStudents()
        assertEquals(1, students?.size)
    }

    @Test
    fun testFindStudentById() {
        val students =  studentService?.findStudentById(1)
        assertEquals(Student(1,"sammidev","2003113948"), students)
    }

    @Test
    fun testFindStudentByNim() {
        val students =  studentService?.findStudentByNim("2003113948")
        assertEquals(Student(1,"sammidev","2003113948"), students)
    }

    @Test
    fun testFindStudentByName() {
        val students =  studentService?.findStudentByName("sammidev")
        assertEquals(Student(1,"sammidev","2003113948"), students)
    }

    @Test
    fun testCountStudents() {
        assertEquals(1, studentService?.countStudents())
    }

    @Test
    fun testCreateStudent() {
        val student = Student(3, "sammidev2", "2003113949")
        studentService?.createStudent(student)
        assertEquals(student, studentService?.findStudentByName("sammidev2"))
    }

    @Test
    fun testDeleteStudentById() {
        val student = Student(3, "sammidev2", "2003113949")
        studentService?.deleteStudentById(student.id)
        assertEquals("sukses delete student dengan id 3", studentService?.deleteStudentById(student.id))
    }

    @Test
    fun testDeleteStudentByName() {
        studentService?.deleteStudentByName("dandi")
        assertEquals("ok", studentService?.deleteStudentByName("dandi"))
    }

    @Test
    fun testDeleteStudentByNim() {
        studentService?.deleteStudentByNim("2003113030")
        assertEquals("ok", studentService?.deleteStudentByNim("2003113030"))
    }

    @Test
    fun testUpdateStudent() {
        val newStudent = Student(1,"Sammi Aldhi Yanto", "2003113948")
        studentService?.updateStudent(newStudent)
        assertEquals(newStudent, studentService?.updateStudent(newStudent))
    }
}