package com.example.attendancesystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class EnrolledStudentAdapter(
    private var students: List<EnrolledStudent>
) : RecyclerView.Adapter<EnrolledStudentAdapter.StudentViewHolder>() {

    /**
     * The ViewHolder class holds the views for a single student row
     * (item_enrolled_student.xml or a similar layout).
     */
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // References to the TextViews in item_enrolled_student.xml
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewStudentName)
        private val rollNoTextView: TextView = itemView.findViewById(R.id.textViewStudentRollNo)

        fun bind(student: EnrolledStudent) {
            // Bind data from the EnrolledStudent data class to the views
            nameTextView.text = student.studentName
            rollNoTextView.text = "Roll No: ${student.rollNumber}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        // Inflate the layout for the single row
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_enrolled_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }

    // Optional: Public method to update the data list if needed
    fun updateList(newList: List<EnrolledStudent>) {
        students = newList
        notifyDataSetChanged()
    }
}
