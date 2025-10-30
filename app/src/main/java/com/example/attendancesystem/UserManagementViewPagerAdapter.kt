package com.example.attendancesystem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter to manage the Teachers and Students tabs in UserManagementActivity.
 * It connects the TabLayout to the Fragments (the lists).
 */
class UserManagementViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    // Number of tabs (Teachers and Students)
    override fun getItemCount(): Int = 2

    // Returns the correct Fragment for the given position (tab index)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TeacherListFragment() // First tab: Teachers
            1 -> StudentListFragment() // Second tab: Students
            else -> TeacherListFragment() // Should not happen
        }
    }
}
