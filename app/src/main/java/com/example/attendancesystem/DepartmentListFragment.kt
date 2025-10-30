package com.example.attendancesystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attendancesystem.R

/**
 * This Fragment displays the list of all departments for the Admin's Academic Management tab.
 * It is correctly set up to use the R.layout.fragment_department_list XML.
 */
class DepartmentListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Later: We will find the RecyclerView here and populate the list of departments.
    }
}
