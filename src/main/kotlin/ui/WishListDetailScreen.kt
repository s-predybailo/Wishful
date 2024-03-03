package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import models.WishListModel
import models.WishlistItem

class WishlistDetailScreen(private val wishlist: WishListModel) : Screen {
    @Composable
    override fun Content() {
        WishlistDetailView(wishlist)
    }
}

@Composable
fun WishlistDetailView(wishlist: WishListModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Вишлист: ${wishlist.title}", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(wishlist.items) { item ->
                WishlistItemView(item)
            }
        }
    }
}

@Composable
fun WishlistItemView(item: WishlistItem) {
    Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
    // Здесь вы можете добавить больше деталей о каждом пункте вишлиста
}
