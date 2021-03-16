package ge.tsu.android.myapplication.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.ActivitySettingsBinding
import ge.tsu.android.myapplication.storage.SettingsDataManager

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    val settingsDataManager: SettingsDataManager = SettingsDataManager(this)

    companion object {
        val SHOW_COMPLETED_ELEMENTS = "show_completed_elements"
        val SORT_BY_TITLE = "sort_by_title"
        val SORT_BY_DATE = "sort_by_date"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showCompletedElements.isChecked =
            settingsDataManager.readSettingsData(SHOW_COMPLETED_ELEMENTS)
        binding.sortByTitle.isChecked = settingsDataManager.readSettingsData(SORT_BY_TITLE)
        binding.sortByDate.isChecked = settingsDataManager.readSettingsData(SORT_BY_DATE)

        binding.showCompletedElements.setOnCheckedChangeListener { buttonView, isChecked ->
            settingsDataManager.addSettings(
                SHOW_COMPLETED_ELEMENTS,
                binding.showCompletedElements.isChecked
            )
        }
        binding.sortByTitle.setOnCheckedChangeListener { buttonView, isChecked ->
            settingsDataManager.addSettings(SORT_BY_TITLE, binding.sortByTitle.isChecked)
        }
        binding.sortByDate.setOnCheckedChangeListener { buttonView, isChecked ->
            settingsDataManager.addSettings(SORT_BY_DATE, binding.sortByDate.isChecked)
        }
    }


}