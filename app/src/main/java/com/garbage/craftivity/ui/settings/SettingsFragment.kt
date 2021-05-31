package com.garbage.craftivity.ui.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.garbage.craftivity.R

class SettingsFragment: PreferenceFragmentCompat() {

    private lateinit var language: String
    private lateinit var languangePreference: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_prefenece)

        setLanguage()
    }

    private fun setLanguage(){
        language = resources.getString(R.string.setting_language)
        languangePreference = findPreference<Preference>(language) as Preference
        languangePreference.setOnPreferenceClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
            true
        }
    }
}