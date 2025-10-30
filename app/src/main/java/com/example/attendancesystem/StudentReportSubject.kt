package com.example.attendancesystem

/**
 * Data class to hold the subject information and final attendance numbers for the Admin's report view.
 */
data class StudentReportSubject(
    val subjectCode: String,
    val subjectName: String,
    val attendedClasses: Int,
    val totalClasses: Int
)
