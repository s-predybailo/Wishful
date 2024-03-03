package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import viewmodel.WishlistViewModel

class CreateWishlistScreen(private val viewModel: WishlistViewModel) : Screen {
    @Composable
    override fun Content() {
        CreateWishlistScreenUI(viewModel)
    }
}

@Composable
fun CreateWishlistScreenUI(viewModel: WishlistViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Создать вишлист", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = viewModel.title.value,
            onValueChange = { viewModel.title.value = it },
            label = { Text("Название вишлиста") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.newItemName.value,
            onValueChange = { viewModel.newItemName.value = it },
            label = { Text("Добавить пункт") },
            trailingIcon = {
                IconButton(onClick = { viewModel.addItem() }) {
                    Icon(Icons.Default.Add, contentDescription = "Добавить")
                }
            }
        )
        LazyColumn(modifier = Modifier.fillMaxHeight().weight(1f)) {
            items(viewModel.items) { item ->
                Text(item.name)
            }
        }
        Button(
            onClick = { viewModel.onWishlistCreated() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Создать")
        }
    }
}


