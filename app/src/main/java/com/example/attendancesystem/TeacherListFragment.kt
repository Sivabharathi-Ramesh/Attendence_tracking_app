package com.example.attendancesystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.attendancesystem.R

/**
 * This Fragment displays the list of all teachers for the Admin's User Management tab.
 * It is correctly set up to use the R.layout.fragment_teacher_list XML.
 */
class TeacherListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Later: We will add a RecyclerView here to display the list of teachers.
    }
}
