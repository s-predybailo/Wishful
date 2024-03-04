package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import models.WishlistItem

@Composable
fun EditItemDialog(item: WishlistItem, onDismiss: () -> Unit, onSave: (WishlistItem) -> Unit) {
    var newName by remember { mutableStateOf(item.name) }
    var newDescription by remember { mutableStateOf(item.description ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Редактирование элемента") },
        text = {
            Column {
                TextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Название") }
                )
                TextField(
                    value = newDescription,
                    onValueChange = { newDescription = it },
                    label = { Text("Описание") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(WishlistItem(item.id, newName, newDescription.ifEmpty { null }, item.link, item.cost))
                    onDismiss()
                }
            ) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}