package ge.tsu.android.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.tsu.android.myapplication.databinding.ActivitySettingsBinding
import ge.tsu.android.myapplication.recycle.RecyclerViewActivity
import java.util.ArrayList

class Settings : AppCompatActivity() {
  private lateinit var binding: ActivitySettingsBinding

  companion object {
    const val INTENT_SETTINGS_KEY = "settings"
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding= ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.back.setOnClickListener{
      val settingsIntent = Intent(this, RecyclerViewActivity::class.java)
      var selectedSettings = ArrayList<String>()
      selectedSettings.add(binding.sortByTitle.text.toString())
      selectedSettings.add(binding.sortByDate.text.toString())
      selectedSettings.add(binding.showCompletedElements.text.toString())
      selectedSettings.add(binding.showAll.text.toString())
      settingsIntent.putExtra(INTENT_SETTINGS_KEY,selectedSettings)
      startActivity(settingsIntent)
    }
  }
}