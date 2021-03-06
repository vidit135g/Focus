package com.absolute.launcher.settings

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.absolute.launcher.*

private val TAB_TITLES = arrayOf(
    R.string.settings_tab_app,
    R.string.settings_tab_theme,
    R.string.settings_tab_launcher
)

/** Returns the fragment corresponding to the selected tab.*/
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> SettingsFragmentApps()
            1 -> SettingsFragmentTheme()
            2 -> SettingsFragmentMeta()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int { return 3 }
}