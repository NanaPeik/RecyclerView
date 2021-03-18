package ge.tsu.android.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.tsu.android.myapplication.databinding.ActivityEditBinding
import ge.tsu.android.myapplication.keys.ExtraKeys
import java.util.*
import kotlin.collections.ArrayList

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        binding.itemNumber.text = "Edit " + intent.getStringExtra(ExtraKeys.INTENT_ITEM_NUMBER)
        binding.editTitleText.setText(intent.getStringExtra(ExtraKeys.INTENT_ITEM_TITLE))
        binding.editDetailsText.setText(intent.getStringExtra(ExtraKeys.INTENT_ITEM_DETAILS))

        binding.editItemButton.setOnClickListener {
            val editIntent = Intent(this@EditActivity, RecyclerViewActivity::class.java)
            var editObjectList = ArrayList<String>()
            editObjectList.add(intent.getStringExtra(ExtraKeys.INTENT_ITEM_NUMBER).toString())
            editObjectList.add(binding.editTitleText.text.toString())
            editObjectList.add(intent.getStringExtra(ExtraKeys.INTENT_ITEM_CHECKED).toString())
            editObjectList.add(binding.editDetailsText.text.toString())
            editObjectList.add(Date().toString())
            editIntent.putExtra(ExtraKeys.INTENT_EDIT_ITEM, editObjectList)
            startActivity(editIntent)
        }
    }
}