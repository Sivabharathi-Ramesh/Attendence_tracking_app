package com.example.attendancesystem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter to manage the Departments and Subjects tabs in AcademicManagementActivity.
 * It connects the TabLayout to the Fragments (the lists).
 */
class AcademicManagementViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    // Number of tabs (Departments and Subjects)
    override fun getItemCount(): Int = 2

    // Returns the correct Fragment for the given position (tab index)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DepartmentListFragment() // First tab: Departments
            1 -> SubjectListFragment()    // Second tab: Subjects
            else -> DepartmentListFragment() // Should not happen
        }
    }
}
