package com.example.attendancesystem

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class StudentSubjectAdapter(
    private val subjects: List<StudentSubject>,
    private val onClick: (StudentSubject) -> Unit
) : RecyclerView.Adapter<StudentSubjectAdapter.SubjectViewHolder>() {

    private lateinit var context: Context // Store context for accessing color resources

    /**
     * The ViewHolder class.
     * This holds the view references for a single row (item_student_subject.xml).
     */
    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get references to the views in our item layout
        val subjectName: TextView = itemView.findViewById(R.id.textViewSubjectName)
        val percentageText: TextView = itemView.findViewById(R.id.textViewPercentage)
        val ratioText: TextView = itemView.findViewById(R.id.textViewRatio)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBarAttendance)

        fun bind(subject: StudentSubject, onClick: (StudentSubject) -> Unit, context: Context) {
            subjectName.text = subject.subjectName

            // 1. Calculate percentage
            val percentage = if (subject.totalClasses > 0) {
                (subject.attendedClasses.toDouble() / subject.totalClasses.toDouble()) * 100
            } else {
                0.0
            }

            percentageText.text = "${percentage.toInt()}%"
            ratioText.text = "${subject.attendedClasses} / ${subject.totalClasses}"
            progressBar.progress = percentage.toInt()

            // 2. Set the color-coding for the progress bar
            val colorResource = if (percentage >= 75) {
                R.color.positive_green // Green for 75% or higher
            } else {
                R.color.negative_red // Red for below 75%
            }

            // Apply color filter to the progress bar drawable
            progressBar.progressDrawable.setColorFilter(
                ContextCompat.getColor(context, colorResource),
                PorterDuff.Mode.SRC_IN
            )

            // Set a click listener for the whole item view
            itemView.setOnClickListener {
                onClick(subject)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        // Store the context from the parent view
        context = parent.context
        // Inflate the layout for a single row
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_student_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjects[position], onClick, context)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}
