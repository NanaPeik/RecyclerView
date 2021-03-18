package ge.tsu.android.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.tsu.android.myapplication.databinding.ActivityDetilesBinding

class DetilesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetilesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = getIntent()

        binding.numeber.text = intent.getIntExtra("number", 0).toString()
        binding.title.text = intent.getStringExtra("title")
        binding.detiles.text = intent.getStringExtra("detiles")
        binding.inputDate.text = intent.getStringExtra("date")
        if (intent.getBooleanExtra(
                "checked",
                false
            )
        ) binding.isChecked.text = "This item is completed"
        else binding.isChecked.text = "This item is not completed"

    }
}