package com.example.attendancesystem

/**
 * Data class to hold the attendance details for a single day.
 * This is for the Student's detailed log view.
 */
data class StudentAttendanceDetail(
    val date: String,    // e.g., "Oct 24, 2025"
    val status: String   // e.g., "Present", "Absent Informed", "Absent Uninformed"
)
