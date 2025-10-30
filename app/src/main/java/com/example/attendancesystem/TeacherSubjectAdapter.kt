package com.example.attendancesystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class TeacherSubjectAdapter(
    private var subjects: List<TeacherSubject>,
    private val onClick: (TeacherSubject) -> Unit
) : RecyclerView.Adapter<TeacherSubjectAdapter.SubjectViewHolder>() {

    /**
     * The ViewHolder class.
     * This holds the view references for a single row (item_subject_teacher.xml).
     */
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get references to the TextViews in our item layout
        private val subjectNameTextView: TextView = itemView.findViewById(R.id.textViewSubjectName)
        private val subjectDetailsTextView: TextView = itemView.findViewById(R.id.textViewSubjectDetails)

        fun bind(subject: TeacherSubject, onClick: (TeacherSubject) -> Unit) {
            // Bind data from the TeacherSubject data class to the views
            subjectNameTextView.text = subject.subjectName
            subjectDetailsTextView.text = subject.subjectDetails

            // Set a click listener for the whole item view
            itemView.setOnClickListener {
                onClick(subject)
            }
        }
    }

    /**
     * Called when RecyclerView needs a new ViewHolder.
     * This inflates (creates) our row layout (item_subject_teacher.xml).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        // Inflate the layout for a single row
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject_teacher, parent, false)
        return SubjectViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        holder.bind(subject, onClick)
    }

    /**
     * Returns the total number of items in the list.
     */
    override fun getItemCount(): Int {
        return subjects.size
    }
}
