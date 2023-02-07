package ru.aston.moroz_lesson1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class MenuItems : Parcelable {
    data class Pizza(val name: String, val size: Int, val quantity: Int): MenuItems()
    data class Burger(val name: String, val quantity: Int): MenuItems()
    data class HotDog(val name: String, val size: Int): MenuItems()
}