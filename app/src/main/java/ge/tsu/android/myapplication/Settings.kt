package ge.tsu.android.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.android.myapplication.databinding.ActivitySettingsBinding
import ge.tsu.android.myapplication.recycle.ExtraKeys
import ge.tsu.android.myapplication.recycle.ListDataManager

class Settings : AppCompatActivity() {
  private lateinit var binding: ActivitySettingsBinding

  companion object {
    const val INTENT_SETTINGS_KEY = "settings"
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val listDataManager =
      ListDataManager<ArrayList<Boolean>>(ExtraKeys.SHARED_PREFERENCES_SETTINGS, this)

  }

}