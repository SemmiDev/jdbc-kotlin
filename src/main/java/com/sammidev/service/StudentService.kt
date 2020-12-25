package com.sammidev.service

import com.sammidev.connection.ConnectionToDatabase
import com.sammidev.entity.Student
import com.sammidev.repository.StudentRepository
import java.sql.*

class StudentService(val connection: Connection): StudentRepository {

    override fun findStudentById(id: Int): Student? {
        val statement: Statement = this.connection.createStatement()
        val sql = "SELECT * FROM table_student where id=$id"
        val result: ResultSet = statement.executeQuery(sql)
        var student: Student? = null

        while (result.next()) {
            val id = result.getInt("id")
            val name = result.getString("name")
            val nim = result.getString("nim")
            student = Student(id,name,nim)
        }
        return student
    }

    override fun findStudentByNim(nimParam: String): Student? {
        val statement: Statement = this.connection.createStatement()
        val sql = "SELECT * FROM table_student where nim like '$nimParam'"
        val result: ResultSet = statement.executeQuery(sql)
        var student: Student? = null

        while (result.next()) {
            val id = result.getInt("id")
            val name = result.getString("name")
            val nim = result.getString("nim")
            student = Student(id,name,nim)
        }
        return student
    }

    override fun findStudentByName(nameParam: String): Student? {
        val statement: Statement = this.connection.createStatement()
        val sql = "SELECT * FROM table_student where name = '$nameParam'"
        val result: ResultSet = statement.executeQuery(sql)
        var student: Student? = null

        while (result.next()) {
            val id = result.getInt("id")
            val name = result.getString("name")
            val nim = result.getString("nim")
            student = Student(id,name,nim)
        }

        return student
    }

    override fun findStudents(): List<Student?>? {

        val statement: Statement = this.connection.createStatement()
        val sql = "SELECT * FROM table_student"

        try {
            val result: ResultSet = statement.executeQuery(sql)
            val listOfStudent = mutableListOf<Student>()
            var index = 0
            while (result.next()) {
                val id = result.getInt("id")
                val name = result.getString("name")
                val nim = result.getString("nim")

                listOfStudent.add(index, Student(id,name,nim))
                index++
            }
            return listOfStudent
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return null
    }

    override fun countStudents(): Int = findStudents()!!.size

    override fun createStudent(student: Student): Student? {
        val sql = "INSERT INTO table_student(id,name,nim) VALUES (?,?,?)"

        try {
            val preparedStatement: PreparedStatement = this.connection.prepareStatement(sql)

            preparedStatement.setInt(1, student.id)
            preparedStatement.setString(2, student.name)
            preparedStatement.setString(3, student.nim)

            preparedStatement.executeUpdate()
            return Student(student.id, student.name, student.nim)
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return null
    }

    override fun deleteStudentById(id: Int): String {
        val sql = "DELETE FROM table_student WHERE ID = ?"
        try {
            val preparedStatement: PreparedStatement = this.connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
            return "sukses delete student dengan id $id"
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return "gagal delete, id takde yg cocok la"
    }

    override fun deleteStudentByName(name: String): String {
        val sql = "DELETE FROM table_student WHERE name = '$name'"
        try {
            val statement: Statement = this.connection.createStatement()
            statement.executeUpdate(sql)
            return "ok"
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return "gagal"
    }

    override fun deleteStudentByNim(nim: String): String {
        val sql = "DELETE FROM table_student WHERE nim = '$nim'"
        try {
            val statement: Statement = this.connection.createStatement()
            statement.executeUpdate(sql)
            return "ok"
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return "gagal"
    }

    override fun updateStudent(student: Student): Student? {
        val sql = "UPDATE table_student set name = ?, nim = ? where id = ?"
        try {
            val preparedStatement: PreparedStatement = this.connection.prepareStatement(sql)
            preparedStatement.setString(1, student.name)
            preparedStatement.setString(2, student.nim)
            preparedStatement.setInt(3, student.id)

            preparedStatement.executeUpdate()
            return student
        }catch (ex: SQLException) {
            ConnectionToDatabase().printSQLException(ex)
        }
        return null
    }
}