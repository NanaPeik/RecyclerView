package ge.tsu.android.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.android.myapplication.R
import ge.tsu.android.myapplication.databinding.ActivityDetailsBinding
import ge.tsu.android.myapplication.keys.ExtraKeys
import java.util.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        binding.numeber.text = intent.getIntExtra(ExtraKeys.INTENT_ITEM_NUMBER, 0).toString()
        binding.title.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_TITLE)
        binding.details.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_DETAILS)
        binding.inputDate.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_DATE)
        if (intent.getBooleanExtra(
                ExtraKeys.INTENT_ITEM_CHECKED,
                false
            )
        ) binding.isChecked.text = resources.getString(R.string.completed_item)
        else binding.isChecked.text = resources.getString(R.string.not_completed_item)


        binding.deleteItem.setOnClickListener {
            val intent = Intent(this@DetailsActivity, RecyclerViewActivity::class.java)
            intent.putExtra(ExtraKeys.INTENT_DELETE_ITEM, binding.numeber.text)
            startActivity(intent)
        }

        binding.editItem.setOnClickListener {
            val intentEdit = Intent(this@DetailsActivity, EditActivity::class.java)

            intentEdit.putExtra(ExtraKeys.INTENT_ITEM_NUMBER, binding.numeber.text)
            intentEdit.putExtra(ExtraKeys.INTENT_ITEM_TITLE, binding.title.text)
            intentEdit.putExtra(ExtraKeys.INTENT_ITEM_DETAILS, binding.details.text)
            intentEdit.putExtra(ExtraKeys.INTENT_ITEM_CHECKED,binding.isChecked.text)
            intentEdit.putExtra(ExtraKeys.INTENT_ITEM_DATE,binding.inputDate.text)
            startActivity(intentEdit)

        }
    }
}