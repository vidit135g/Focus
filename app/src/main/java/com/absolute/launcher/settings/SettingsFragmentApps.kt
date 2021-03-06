package com.absolute.launcher.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.launcher.ChooseActivity
import com.absolute.launcher.R
import com.absolute.launcher.extern.*
import com.absolute.launcher.intendedSettingsPause
import com.absolute.launcher.settings.actions.ActionsRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_settings_apps.*


/** The 'Apps' Tab associated Fragment in Settings */

class SettingsFragmentApps : Fragment() {

    /** Lifecycle functions */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_apps, container, false)
    }

    override fun onStart() {

        if (getSavedTheme(context!!) == "custom") {

            fragment_settings_apps_container.setBackgroundColor(dominantColor)
        }
        else{
            fragment_settings_apps_install_btn.setStrokeColorResource(R.color.finnmglasTheme_accent_color)
            fragment_settings_apps_btn.setStrokeColorResource(R.color.finnmglasTheme_accent_color)
            fragment_settings_apps_btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.finnmglasTheme_accent_color))
            fragment_settings_apps_install_btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.finnmglasTheme_accent_color))
        }


        // set up the list / recycler
        val actionViewManager = LinearLayoutManager(context)
        val actionViewAdapter = ActionsRecyclerAdapter( activity!! )

        activity_settings_actions_recycler_view.apply {
            // improve performance (since content changes don't change the layout size)
            setHasFixedSize(true)
            layoutManager = actionViewManager
            adapter = actionViewAdapter
        }

        // App management buttons
        fragment_settings_apps_btn.setOnClickListener{
            val intent = Intent(this.context, ChooseActivity::class.java)
            intent.putExtra("action", "view")
            intendedSettingsPause = true
            startActivity(intent)
        }
        fragment_settings_apps_install_btn.setOnClickListener{
            try {
                val rateIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/"))
                startActivity(rateIntent)
                intendedSettingsPause = true
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this.context, getString(R.string.settings_toast_store_not_found), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        super.onStart()
    }
}