package com.sammidev.connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionToDatabase {

    var port = "5432"
    var db = "postgresql"
    var host = "localhost"
    var dbName = "student"
    var platform = "jdbc"
    var user = "postgres"
    var password = "sammidev"

    var url = "$platform:$db://$host:$port/$dbName"

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

        port = "3306"
        db = "mysql"
        host = "localhost"
        dbName = "student"
        platform = "jdbc"
        user = "root"
        password = ""

        url = "$platform:$db://$host:$port/$dbName"

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