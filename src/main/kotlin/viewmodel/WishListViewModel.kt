package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import models.WishListModel
import models.WishlistItem

class WishlistViewModel {
    var title = mutableStateOf("")
    var items = mutableStateListOf<WishlistItem>()
    var newItemName = mutableStateOf("")

    // Список вишлистов для отображения на главном экране
    var wishlists = mutableStateListOf<WishListModel>()

    fun addItem() {
        if (newItemName.value.isNotBlank()) {
            items.add(WishlistItem(id = items.size + 1, name = newItemName.value, description = null, link = null, cost = null))
            newItemName.value = ""
        }
    }

    fun createWishlist() {
        if (title.value.isNotBlank() && items.isNotEmpty()) {
            val newWishlist = WishListModel(id = wishlists.size + 1, title = title.value, items = items.toList())
            wishlists.add(newWishlist)
            // Очистка полей после создания вишлиста
            title.value = ""
            items.clear()
        }
    }

    // Метод для обработки выбора вишлиста, если вам нужно выполнить какие-либо действия при выборе
    fun onWishlistSelected(wishlist: WishListModel) {
        // Здесь может быть логика обработки выбранного вишлиста
        // Например, открытие экрана деталей вишлиста или его редактирование
    }

    // Этот метод теперь использует данные из параметров для создания вишлиста
    fun onWishlistCreated() {
        createWishlist()
    }
}

