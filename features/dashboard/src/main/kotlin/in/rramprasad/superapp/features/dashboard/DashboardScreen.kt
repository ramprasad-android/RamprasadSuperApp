package `in`.rramprasad.superapp.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavEntry
import `in`.rramprasad.superapp.core.navigation.Destination
import `in`.rramprasad.superapp.core.navigation.NavEntryProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onAppSelected: (Destination) -> Unit) {
    val apps = listOf(
        AppItem("Smart Home", Icons.Default.Home, Destination.Home, Color(0xFF4CAF50)),
        AppItem("Maps", Icons.Default.Place, Destination.Maps, Color(0xFF2196F3)),
        AppItem("Finance", Icons.Default.AccountBalanceWallet, Destination.PersonalFinance, Color(0xFFFF9800)),
        AppItem("Profile", Icons.Default.Person, Destination.Profile, Color(0xFF9C27B0)),
        AppItem("Settings", Icons.Default.Settings, Destination.Profile, Color(0xFF607D8B)),
        AppItem("Notifications", Icons.Default.Notifications, Destination.Notifications, Color(0xFFF44336))
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ramprasad Super App", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Search Bar Placeholder (Amazon/Flipkart style)
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                    Spacer(Modifier.width(8.dp))
                    Text("Search for apps or features...", color = Color.Gray)
                }
            }

            Text(
                "Your Apps",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(apps) { app ->
                    AppGridItem(app) { onAppSelected(app.destination) }
                }
            }
        }
    }
}

@Composable
fun AppGridItem(app: AppItem, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Surface(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(16.dp),
            color = app.color.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = app.icon,
                    contentDescription = app.name,
                    tint = app.color,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = app.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

data class AppItem(
    val name: String,
    val icon: ImageVector,
    val destination: Destination,
    val color: Color
)

class DashboardNavEntryProvider(
    private val onAppSelected: (Destination) -> Unit
) : NavEntryProvider {
    override fun provideEntry(key: Destination): NavEntry<out Destination>? {
        return if (key is Destination.Dashboard) {
            NavEntry(key) {
                DashboardScreen(onAppSelected = onAppSelected)
            }
        } else null
    }
}
