package com.sammidev.service

import com.sammidev.entity.Student
import com.sammidev.repository.StudentRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(value = [MockitoExtension::class])
class StudentServiceTestMock {

    @Mock
    lateinit var studentRepository: StudentRepository
    lateinit var studentServiceForMock: StudentServiceForMock

    @BeforeEach
    fun createConnection() {
        studentServiceForMock = StudentServiceForMock(studentRepository)
    }

    @Test
    fun testFindStudentByIdSuccess() {

        Mockito.`when`(studentRepository.findStudentById(2))
                .thenReturn(Student(2, "sammidev", "2003113948"))

        val student = studentServiceForMock.findStudentById(2)

        assertNotNull(student)
        assertEquals(Student(2, "sammidev", "2003113948"), student)
    }

    @Test
    fun testFindStudentsSuccess() {
        Mockito.`when`(studentRepository.findStudents())
                .thenReturn(listOf(
                        Student(1, "sammi1", "001"),
                        Student(2, "sammi12", "002"),
                        Student(3, "sammi3", "003"),
                        Student(4, "sammi4", "004")
                ))
        val listofstudent = studentServiceForMock.findStudents()

        assertNotNull(listofstudent)
        assertEquals(4, listofstudent.size)
    }

    @Test
    fun testFindStudentByNim() {
        Mockito.`when`(studentRepository.findStudentByNim("200"))
                .thenReturn(Student(1, "sammi", "200"))

        val listofstudent = studentServiceForMock.findStudentByNim("200")
        assertNotNull(listofstudent)
        assertEquals(listofstudent.name, listofstudent.name)
    }

    @Test
    fun testFindStudentByName() {
        Mockito.`when`(studentRepository.findStudentByName("sammidev"))
                .thenReturn(Student(1, "sammi", "200"))

        val listofstudent = studentServiceForMock.findStudentByName("sammidev")
        assertNotNull(listofstudent)
        assertEquals(listofstudent, listofstudent)
    }

    @Test
    fun testCreateStudent() {
        Mockito.`when`(studentRepository.createStudent(Student(1, "sam", "200")))
                .thenReturn(Student(1, "sam", "200"))

        val listofstudent = studentServiceForMock.createStudent(Student(1, "sam", "200"))
        assertNotNull(listofstudent)
        assertEquals(listofstudent, listofstudent)
    }

    @Test
    fun testDeleteStudentById() {
        Mockito.`when`(studentRepository.deleteStudentById(1))
                .thenReturn("ok")

        val listofstudent = studentServiceForMock.studentRepository.deleteStudentById(1)
        assertEquals("ok", listofstudent)
    }

    @Test
    fun testDeleteStudentByName() {
        Mockito.`when`(studentRepository.deleteStudentByName("sam"))
                .thenReturn("ok")

        val listofstudent = studentServiceForMock.studentRepository.deleteStudentByName("sam")
        assertEquals("ok", listofstudent)
    }

    @Test
    fun testDeleteStudentByNim() {
        Mockito.`when`(studentRepository.deleteStudentByNim("200"))
                .thenReturn("ok")

        val listofstudent = studentServiceForMock.studentRepository.deleteStudentByNim("200")
        assertEquals("ok", listofstudent)
    }

    @Test
    fun testUpdateStudent() {
        Mockito.`when`(studentRepository.updateStudent(Student(1,"sam","200")))
                .thenReturn(Student(1,"sam","200"))

        val listofstudent = studentServiceForMock.studentRepository.updateStudent(Student(1,"sam","200"))
        assertEquals(1, listofstudent?.id)
    }

    @Test
    fun testCountStudent() {
        Mockito.`when`(studentRepository.countStudents())
                .thenReturn(100)

        val listofstudent = studentServiceForMock.studentRepository.countStudents()
        assertEquals(100, listofstudent)
    }
}