package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
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

    fun getWishlistById(wishlistId: Int): WishListModel? {
        return wishlists.firstOrNull { it.id == wishlistId }
    }

    fun getItemById(wishlistId: Int, itemId: Int): WishlistItem? {
        return wishlists.firstOrNull { it.id == wishlistId }?.items?.firstOrNull { it.id == itemId }
    }

    fun editItem(wishlistId: Int, editedItem: WishlistItem) {
        // Находим вишлист по ID
        val wishlistIndex = wishlists.indexOfFirst { it.id == wishlistId }
        if (wishlistIndex != -1) {
            // Получаем вишлист
            val wishlist = wishlists[wishlistIndex]
            // Находим индекс редактируемого элемента в вишлисте
            val itemIndex = wishlist.items.indexOfFirst { it.id == editedItem.id }
            if (itemIndex != -1) {
                // Создаем новый список элементов с обновленным элементом
                val updatedItems = wishlist.items.toMutableList().apply {
                    this[itemIndex] = editedItem
                }
                // Создаем новый вишлист с обновленным списком элементов
                val updatedWishlist = wishlist.copy(items = updatedItems)
                // Обновляем вишлист в списке вишлистов
                wishlists[wishlistIndex] = updatedWishlist
                wishlists = wishlists.toMutableStateList()
            }
        }
    }

    fun deleteItem(wishlistId: Int, item: WishlistItem) {
        // Находим вишлист по ID
        val wishlistIndex = wishlists.indexOfFirst { it.id == wishlistId }
        if (wishlistIndex != -1) {
            // Получаем вишлист
            val wishlist = wishlists[wishlistIndex]
            // Удаляем элемент из списка элементов вишлиста
            val updatedItems = wishlist.items.filter { it.id != item.id }

            if (updatedItems.isEmpty()) {
                // Если после удаления элемента список элементов стал пустым,
                // удаляем сам вишлист из списка вишлистов
                wishlists.removeAt(wishlistIndex)
            } else {
                // В противном случае создаем новый вишлист с обновленным списком элементов
                val updatedWishlist = wishlist.copy(items = updatedItems)
                // Обновляем вишлист в списке вишлистов
                wishlists[wishlistIndex] = updatedWishlist
            }
        }
    }

}

