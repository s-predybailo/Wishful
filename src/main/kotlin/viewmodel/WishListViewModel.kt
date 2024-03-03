package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import models.WishListModel
import models.WishlistItem

class WishlistViewModel {
    var title = mutableStateOf("")
    var items = mutableStateListOf<WishlistItem>()
    var newItemName = mutableStateOf("")

    var wishlists = mutableStateListOf<WishListModel>()

    fun addItem() {
        if (newItemName.value.isNotBlank()) {
            items.add(WishlistItem(id = items.size + 1, name = newItemName.value, description = null, link = null, cost = null))
            newItemName.value = ""
        }
    }

    private fun createWishlist() {
        if (title.value.isNotBlank() && items.isNotEmpty()) {
            val newWishlist = WishListModel(id = wishlists.size + 1, title = title.value, items = items.toList())
            wishlists.add(newWishlist)
            // Очистка полей после создания вишлиста
            title.value = ""
            items.clear()
        }
    }

    fun onWishlistCreated() {
        createWishlist()
    }
}

