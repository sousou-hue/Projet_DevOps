package com.example.tp5

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AbsenceDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "absences.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_ABSENCES = "absences"
        private const val COLUMN_ID = "id"
        private const val COLUMN_CLASS = "class_name"
        private const val COLUMN_STUDENT = "student_name"
        private const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_ABSENCES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CLASS TEXT NOT NULL,
                $COLUMN_STUDENT TEXT NOT NULL,
                $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ABSENCES")
        onCreate(db)
    }

    fun insertAbsence(className: String, studentName: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CLASS, className)
            put(COLUMN_STUDENT, studentName)
        }
        db.insert(TABLE_ABSENCES, null, values)
        db.close()
    }

    fun getAllAbsences(): List<String> {
        val absences = mutableListOf<String>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_ABSENCES ORDER BY $COLUMN_TIMESTAMP DESC"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val className = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASS))
                val studentName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT))
                val timestamp = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP))

                val formatted = "$timestamp - $className - $studentName"
                absences.add(formatted)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return absences
    }
}
