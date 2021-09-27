package op.mobile.app.dev.mandha1.travelling.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import kotlinx.coroutines.launch
import op.mobile.app.dev.mandha1.travelling.R

class SettingsFragment : Fragment() {
    private lateinit var swToggleDarkMode: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        swToggleDarkMode = view.findViewById(R.id.sw_toggle_dark_mode)

        // Instantiating SettingsManager - passing in a context. For Fragments, we use requireContext()...for Activities, we use this
        val settingsManager = SettingsManager(requireContext())

        // Observe changes to UIMode
        settingsManager.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            setCheckedUIMode(it)
        }

        // OnCheckedChangeListener bound to the Switch widget
        swToggleDarkMode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                when (isChecked) {
                    // If checked, set to dark mode, else, set to light mode
                    true -> settingsManager.setUIMode(UIMode.DARK)
                    false -> settingsManager.setUIMode(UIMode.LIGHT)
                }
            }
        }

        return view
    }

    private fun setCheckedUIMode(uiMode: UIMode?) {
        when (uiMode) {
            UIMode.LIGHT -> {
                // This changes the UI mode to light mode. It will use the default themes.xml
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                swToggleDarkMode.isChecked = false
            }
            UIMode.DARK -> {
                // This changes the UI mode to dark mode. It will use the night themes.xml
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                swToggleDarkMode.isChecked = true
            }
        }
    }
}
