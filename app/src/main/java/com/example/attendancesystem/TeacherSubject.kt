package com.example.attendancesystem

/**
 * Data class to hold the essential subject information for a Teacher's Dashboard list.
 */
data class TeacherSubject(
    val subjectId: String,
    val subjectName: String,
    // Details includes Department and Semester (e.g., "SDML | Sem 4")
    val subjectDetails: String
)
