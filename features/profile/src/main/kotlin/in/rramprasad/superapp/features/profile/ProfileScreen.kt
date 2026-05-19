package `in`.rramprasad.superapp.features.profile

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
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (Destination) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text(text = "Profile Screen", fontSize = 24.sp)
        }
    }
}

class ProfileNavEntryProvider(
    private val onScreenSelected: (Destination) -> Unit
) : NavEntryProvider {
    override fun provideEntry(key: Destination): NavEntry<out Destination>? {
        return if (key is Destination.Profile) {
            NavEntry(key) {
                ProfileScreen(onScreenSelected = onScreenSelected)
            }
        } else null
    }
}
