package `in`.rramprasad.superapp.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.rramprasad.superapp.core.navigation.Destination
import `in`.rramprasad.superapp.core.navigation.NavEntryProvider
import androidx.navigation3.runtime.NavEntry

@Composable
fun EspRemoteScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (Destination) -> Unit = {}
) {
    // Note: R.string and other resources will need to be handled, 
    // for now using placeholders or assuming they are moved to a core:ui or similar if needed.
    // Ideally resources are also modularized.
    
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
        contentWindowInsets = WindowInsets.safeDrawing,
        // bottomBar and other UI components might need to be moved to a common UI module 
        // if they are shared across features.
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1B5E37), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
            ) {
                Text(
                    text = "ESP Remote",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Home Content", fontSize = 20.sp)
                Button(onClick = { onScreenSelected(Destination.Devices) }) {
                    Text("Go to Devices")
                }
            }
        }
    }
}

class HomeNavEntryProvider(
    private val onScreenSelected: (Destination) -> Unit
) : NavEntryProvider {
    override fun provideEntry(key: Destination): NavEntry<out Destination>? {
        return if (key is Destination.Home) {
            NavEntry(key) {
                EspRemoteScreen(onScreenSelected = onScreenSelected)
            }
        } else null
    }
}
