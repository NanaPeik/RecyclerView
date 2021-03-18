package ge.tsu.android.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.android.myapplication.databinding.ActivityDetailsBinding
import ge.tsu.android.myapplication.keys.ExtraKeys

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent

        binding.numeber.text = intent.getIntExtra(ExtraKeys.INTENT_ITEM_NUMBER, 0).toString()
        binding.title.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_TITLE)
        binding.details.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_DETILES)
        binding.inputDate.text = intent.getStringExtra(ExtraKeys.INTENT_ITEM_DATE)
        if (intent.getBooleanExtra(
                ExtraKeys.INTENT_ITEM_CHECKED,
                false
            )
        ) binding.isChecked.text = "This item is completed"
        else binding.isChecked.text = "This item is not completed"


        binding.deleteItem.setOnClickListener {
            var intent = Intent(this@DetailsActivity, RecyclerViewActivity::class.java)
            intent.putExtra(ExtraKeys.INTENT_DELETE_ITEM, binding.numeber.text)
            startActivity(intent)
        }
    }
}