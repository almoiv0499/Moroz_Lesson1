package ru.aston.moroz_lesson1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.moroz_lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val MENU_ITEM_KEY = "menu_item_key"
        private const val GET_ORDER_LIST_KEY = "get_order_list_key"
        private const val PIZZA_SIZE = 40
        private const val PIZZA_COUNT = 2
        private const val BURGER_COUNT = 3
        private const val HOTDOG_SIZE = 20
    }

    private lateinit var binding: ActivityMainBinding

    private val orderList = mutableListOf<MenuItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //commit and merge feature2 to development
        //commit2 for feature2 branch

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chooseMenuItem()
        binding.getSizeOfOrder.setOnClickListener {
            binding.orderSize.text = orderList.size.toString()
        }

        //commit and merge (to development branch) for feature branch
    }

    private fun chooseMenuItem() {
        with(binding) {
            toPizzaOrder.setOnClickListener {
                val pizza = MenuItems.Pizza(getString(R.string.name_pizza), PIZZA_SIZE, PIZZA_COUNT)
                orderList.add(pizza)
                navigateToMenuDetailActivity(pizza)
            }
            toBurgerOrder.setOnClickListener {
                val burger = MenuItems.Burger(getString(R.string.burger_name), BURGER_COUNT)
                orderList.add(burger)
                navigateToMenuDetailActivity(burger)

            }
            toHotDogOrder.setOnClickListener {
                val hotDog = MenuItems.HotDog(getString(R.string.hotDog_name), HOTDOG_SIZE)
                orderList.add(hotDog)
                navigateToMenuDetailActivity(hotDog)
            }
        }
    }

    private fun navigateToMenuDetailActivity(menu: MenuItems) {
        when (menu) {
            is MenuItems.Pizza -> {
                val order = String.format(
                    getString(R.string.pizza_info),
                    menu.name,
                    menu.size,
                    menu.quantity
                )
                Intent(this, MenuOrderActivity::class.java).apply {
                    putExtra(MENU_ITEM_KEY, order)
                    startActivity(this)
                }
            }

            is MenuItems.Burger -> {
                val order = String.format(
                    getString(R.string.burger_info),
                    menu.name,
                    menu.quantity
                )
                Intent(this, MenuOrderActivity::class.java).apply {
                    putExtra(MENU_ITEM_KEY, order)
                    startActivity(this)
                }
            }

            is MenuItems.HotDog -> {
                val order = String.format(getString(R.string.hotDog_info), menu.name, menu.size)
                Intent(this, MenuOrderActivity::class.java).apply {
                    putExtra(MENU_ITEM_KEY, order)
                    startActivity(this)
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableArrayList<MenuItems>(GET_ORDER_LIST_KEY)?.let { list ->
            orderList.clear()
            orderList.addAll(list)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelableArrayList(GET_ORDER_LIST_KEY, ArrayList(orderList))
    }


}