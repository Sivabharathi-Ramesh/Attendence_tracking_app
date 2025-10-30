package com.example.attendancesystem

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class StudentDetailAdapter(
    private val details: List<StudentAttendanceDetail>
) : RecyclerView.Adapter<StudentDetailAdapter.DetailViewHolder>() {

    private lateinit var context: Context // Store context for accessing color resources

    /**
     * The ViewHolder class.
     * This holds the view references for a single row (item_student_detail_row.xml).
     */
    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get references to the views in our item layout
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val statusTextView: TextView = itemView.findViewById(R.id.textViewStatus)
        val statusIndicator: View = itemView.findViewById(R.id.statusIndicator)

        fun bind(detail: StudentAttendanceDetail, context: Context) {
            dateTextView.text = detail.date
            statusTextView.text = detail.status

            // Set the color of the text and the dot indicator based on status
            val color = when (detail.status) {
                "Present" -> ContextCompat.getColor(context, R.color.positive_green)
                "Absent Informed" -> ContextCompat.getColor(context, R.color.warning_yellow)
                else -> ContextCompat.getColor(context, R.color.negative_red) // "Absent Uninformed"
            }

            statusTextView.setTextColor(color)
            // Apply color filter to the status indicator dot
            statusIndicator.background.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        // Store the context
        context = parent.context
        // Inflate the layout for a single row
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_student_detail_row, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(details[position], context)
    }

    override fun getItemCount(): Int {
        return details.size
    }
}
