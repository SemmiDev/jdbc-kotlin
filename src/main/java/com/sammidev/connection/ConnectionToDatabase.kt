package com.sammidev.connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionToDatabase {

    var url = "jdbc:postgresql://localhost:5432/student"
    var user = "postgres"
    var password = "sammidev"

    fun createConnectionToPostgres(): Connection? {
        try {
            val connection: Connection = DriverManager.getConnection(url, user, password)
            println("CONNECTED")
            return connection
        } catch (ex: SQLException) {
            printSQLException(ex)
        }
        return null
    }
    fun createConnectionToMysql(): Connection? {

        url = "jdbc:mysql://localhost:3306/student"
        user = "root"
        password = ""

        try {
            val connection: Connection = DriverManager.getConnection(url, user, password)
            println("CONNECTED")
            return connection
        } catch (ex: SQLException) {
            printSQLException(ex)
        }
        return null
    }
    fun printSQLException(ex: SQLException) {
        for (e in ex) {
            if (e is SQLException) {
                e.printStackTrace(System.err)
                System.err.println("SQLState: " + e.sqlState)
                System.err.println("Error Code: " + e.errorCode)
                System.err.println("Message: " + e.message)
                var t = ex.cause
                while (t != null) {
                    println("Cause: $t")
                    t = t.cause
                }
            }
        }
    }
}