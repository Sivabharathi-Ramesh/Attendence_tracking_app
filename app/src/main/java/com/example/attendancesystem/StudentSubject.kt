package com.example.attendancesystem

/**
 * Data class to hold a subject's attendance status for the Student Dashboard list.
 * This data is used to calculate the attendance percentage and determine the progress bar color.
 */
data class StudentSubject(
    val subjectId: String,
    val subjectName: String,
    val attendedClasses: Int,
    val totalClasses: Int
)
