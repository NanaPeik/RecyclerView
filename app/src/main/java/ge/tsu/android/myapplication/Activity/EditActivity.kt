package ge.tsu.android.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.ActivityEditBinding
import ge.tsu.android.myapplication.keys.ExtraKeys
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        binding.itemNumber.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_NUMBER)
        binding.editTitleText.setText(intent.getStringExtra(ExtraKeys.INTENT_ITEM_TITLE))
        binding.editDetailsText.setText(intent.getStringExtra(ExtraKeys.INTENT_ITEM_DETAILS))

        binding.editItemButton.setOnClickListener {
            val editIntent = Intent(this@EditActivity, RecyclerViewActivity::class.java)
            val currentDate = LocalDateTime.now().format(
                DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.LONG, FormatStyle.SHORT
                )
            )
            editIntent.putExtra(
                ExtraKeys.PREVIOUS_TITLE,
                intent.getStringExtra(ExtraKeys.INTENT_ITEM_TITLE)
            )
            editIntent.putExtra(
                ExtraKeys.PREVIOUS_DETAILS,
                intent.getStringExtra(ExtraKeys.INTENT_ITEM_DETAILS)
            )
            editIntent.putExtra(ExtraKeys.PREVIOUS_NUMBER, binding.itemNumber.text)
            if (intent.getStringExtra(ExtraKeys.INTENT_ITEM_CHECKED) == resources.getString(R.string.completed_item)) {
                editIntent.putExtra(ExtraKeys.PREVIOUS_CHECKED, true.toString())
            } else {
                editIntent.putExtra(ExtraKeys.PREVIOUS_CHECKED, false.toString())
            }
            editIntent.putExtra(
                ExtraKeys.PREVIOUS_DATE,
                intent.getStringExtra(ExtraKeys.INTENT_ITEM_DATE)
            )
            editIntent.putExtra(ExtraKeys.EDITED_DATE, currentDate)
            editIntent.putExtra(ExtraKeys.EDITED_TITLE, binding.editTitleText.text.toString())
            editIntent.putExtra(ExtraKeys.EDITED_DETAILS, binding.editDetailsText.text.toString())
            startActivity(editIntent)
        }
    }
}