package ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class CreateWishlistScreen : Screen {
    @Composable
    override fun Content() {
        Text("Создать вишлист")
    }
}