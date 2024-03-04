package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import models.WishListModel
import viewmodel.WishlistViewModel

//class HomeScreen(private val wishlists: List<WishListModel>, private val onWishlistSelected: (WishListModel) -> Unit) : Screen {
class HomeScreen(private val viewModel: WishlistViewModel, private val onWishlistSelected: (WishListModel) -> Unit) : Screen {
    @Composable
    override fun Content() {
        WishlistScreen(viewModel, onWishlistSelected)
    }
}

//@Composable
//fun WishlistScreen(wishlists: List<WishListModel>, onWishlistSelected: (WishListModel) -> Unit) {
//    LazyColumn {
//        items(wishlists) { wishlist ->
//            WishlistCard(wishlist, onWishlistSelected)
//        }
//    }
//}

@Composable
fun WishlistScreen(viewModel: WishlistViewModel, onWishlistSelected: (WishListModel) -> Unit) {
//    val wishlists by viewModel.wishlists.collectAsState()
    LazyColumn {
        items(viewModel.wishlists, key = { it.id }) { wishlist ->
            WishlistCard(wishlist, onWishlistSelected)
        }
    }
}


@Composable
fun WishlistCard(wishlist: WishListModel, onWishlistSelected: (WishListModel) -> Unit) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onWishlistSelected(wishlist) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wishlist.title, style = MaterialTheme.typography.titleMedium)
            // Добавьте здесь дополнительные детали
        }
    }
}
