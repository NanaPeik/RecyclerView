package ge.tsu.android.myapplication.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    private val listDataManaget = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onPause() {
        super.onPause()
        var settings = ArrayMap<String, Boolean>()
        settings["showCompleted"] = binding.showCompletedElements.isChecked
        settings["shrtByTitle"] = binding.sortByTitle.isChecked
        settings["shrtByDate"] = binding.sortByDate.isChecked

        listDataManaget.addSettings(settings)

    }
}