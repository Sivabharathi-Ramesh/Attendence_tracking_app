package com.example.attendancesystem

/**
 * Data class to hold basic student identification information (Name and Roll Number).
 * Used for read-only lists like the Teacher's Subject Roster header or Admin's User Management tabs.
 */
data class EnrolledStudent(
    val studentName: String,
    val rollNumber: String
)
