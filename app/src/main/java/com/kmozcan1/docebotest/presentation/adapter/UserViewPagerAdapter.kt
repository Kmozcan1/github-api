package com.kmozcan1.docebotest.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kmozcan1.docebotest.ui.ProfileFragment
import com.kmozcan1.docebotest.ui.RepositoriesFragment
import com.kmozcan1.docebotest.ui.UserViewPagerFragment
import javax.inject.Inject

/**
 * Created by Kadir Mert Özcan on 03-Jan-21.
 */
class UserViewPagerAdapter @Inject constructor(
    userViewPagerFragment: UserViewPagerFragment
) : FragmentStateAdapter(userViewPagerFragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return ProfileFragment()
        } else if (position == 1) {
            return RepositoriesFragment()
        }
        return ProfileFragment()
    }
}