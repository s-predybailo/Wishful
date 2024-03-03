import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import ui.CreateWishlistScreen
import ui.HomeScreen

@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
        content = content
    )
}

@Composable
fun App() {
    val navigator = remember { mutableStateOf<Navigator?>(null) }
    val darkTheme = remember { mutableStateOf(false) } // Добавлено состояние для переключения темы

    AppTheme(darkTheme.value) {
        Scaffold { paddingValues ->
            Row(modifier = Modifier.padding(paddingValues)) {
                NavigationRail {
                        NavigationRailItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            label = { Text("Home") },
                            selected = navigator.value?.lastItem is HomeScreen,
                            onClick = {
                                navigator.value?.replaceAll(HomeScreen())
                            }
                        )
                        NavigationRailItem(
                            icon = { Icon(Icons.Default.Create, contentDescription = "Create Wishlist") },
                            label = { Text("Create Wishlist") },
                            selected = navigator.value?.lastItem is CreateWishlistScreen,
                            onClick = {
                                navigator.value?.replaceAll(CreateWishlistScreen())
                            }
                        )
                        Spacer(Modifier.weight(1f))
                        Switch(
                            checked = darkTheme.value,
                            onCheckedChange = { darkTheme.value = it }
                        )
                }
                // Основное содержимое приложения
                Navigator(screen = HomeScreen()) { nav ->
                    navigator.value = nav
                    FadeTransition(nav)
                }
            }
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Wishful") {
        App()
    }
}

