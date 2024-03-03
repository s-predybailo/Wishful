package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import models.WishListModel

class HomeScreen(private val wishlists: List<WishListModel>, private val onWishlistSelected: (WishListModel) -> Unit) : Screen {
    @Composable
    override fun Content() {
        HomeScreenUI(wishlists, onWishlistSelected)
    }
}


@Composable
fun HomeScreenUI(wishlists: List<WishListModel>, onWishlistSelected: (WishListModel) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Ваши вишлисты", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(wishlists) { wishlist ->
                WishlistItemView(wishlist, onWishlistSelected)
            }
        }
    }
}

@Composable
fun WishlistItemView(wishlist: WishListModel, onWishlistSelected: (WishListModel) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onWishlistSelected(wishlist) }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(wishlist.title, style = MaterialTheme.typography.labelMedium)
            // Дополнительные детали вишлиста
        }
    }
}
