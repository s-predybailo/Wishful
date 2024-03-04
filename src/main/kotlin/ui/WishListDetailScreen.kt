package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import models.WishListModel
import viewmodel.WishlistViewModel


class WishlistDetailScreen(private val wishlist: WishListModel, private val viewModel: WishlistViewModel) : Screen {
    @Composable
    override fun Content() {
        WishlistDetailView(wishlist, viewModel)
    }
}

@Composable
fun WishlistDetailView(wishlist: WishListModel, viewModel: WishlistViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Вишлист: ${wishlist.title}", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(wishlist.items, key = { item -> item.id }) { item ->
                WishlistItemView(wishlist.id, item.id, viewModel)
            }
        }
    }
}

@Composable
fun WishlistItemView(wishlistId: Int, itemId: Int, viewModel: WishlistViewModel) {
    val item = viewModel.getItemById(wishlistId, itemId)
    var showEditDialog by remember { mutableStateOf(false) }

    item?.let {
        if (showEditDialog) {
            EditItemDialog(
                item = it,
                onDismiss = { showEditDialog = false },
                onSave = { editedItem ->
                    viewModel.editItem(wishlistId, editedItem)
                    showEditDialog = false
                }
            )
        }

//        Column(modifier = Modifier.padding(8.dp)) {
//            Text(text = it.name, style = MaterialTheme.typography.bodyLarge)
//            WishlistItemOptions({ showEditDialog = true }, {
//                viewModel.deleteItem(wishlistId, it)
//            })
//        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shadowElevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                item.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { showEditDialog = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { viewModel.deleteItem(wishlistId, item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }

    }
}


@Composable
fun WishlistItemOptions(onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}