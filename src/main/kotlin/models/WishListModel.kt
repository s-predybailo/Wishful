package models
data class WishListModel(val id: Int, val title: String, val items: List<WishlistItem>)
data class WishlistItem(val id: Int, val name: String, val description: String?, val link: String?, val cost: Double?)
