package ru.aston.moroz_lesson1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.moroz_lesson1.databinding.ActivityMenuOrderBinding

class MenuOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuItems = intent.getStringExtra(MainActivity.MENU_ITEM_KEY)
        menuItems?.let { order ->
            binding.menuOrder.text = order
        }
    }
}