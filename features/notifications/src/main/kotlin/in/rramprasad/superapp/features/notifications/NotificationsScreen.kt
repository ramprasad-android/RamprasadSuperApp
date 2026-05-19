package `in`.rramprasad.superapp.features.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.rramprasad.superapp.core.navigation.Destination
import `in`.rramprasad.superapp.core.navigation.NavEntryProvider
import androidx.navigation3.runtime.NavEntry

@Composable
fun NotificationsScreen(
    onBack: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text(text = "Notifications Screen", fontSize = 24.sp)
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}

class NotificationsNavEntryProvider(
    private val onBack: () -> Unit
) : NavEntryProvider {
    override fun provideEntry(key: Destination): NavEntry<out Destination>? {
        return if (key is Destination.Notifications) {
            NavEntry(key) {
                NotificationsScreen(onBack = onBack)
            }
        } else null
    }
}
