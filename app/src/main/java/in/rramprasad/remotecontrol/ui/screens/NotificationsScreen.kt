package `in`.rramprasad.remotecontrol.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.rramprasad.remotecontrol.ui.theme.RemoteControlTheme

data class NotificationItem(
    val title: String,
    val message: String,
    val time: String,
    val isRead: Boolean = false
)

@Composable
fun NotificationsScreen(
    onBack: () -> Unit = {}
) {
    val notifications = remember {
        mutableStateListOf(
            NotificationItem("Device Offline", "ESP32 Bedroom has disconnected.", "2 mins ago"),
            NotificationItem("High Temperature", "Soil temperature in Garden is 35°C.", "15 mins ago"),
            NotificationItem("Update Available", "Firmware version 2.1 is available for download.", "1 hour ago", true),
            NotificationItem("Water Level Low", "Water tank level is below 10%.", "3 hours ago", true),
            NotificationItem("Light Scheduled", "Bedroom light scheduled to turn on at 6 PM.", "5 hours ago", true)
        )
    }

    Scaffold(
        containerColor = Color(0xFFF1F8F1),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1B5E37), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.15f), CircleShape)
                    ) {
                        // Back arrow placeholder
                        Box(modifier = Modifier.size(12.dp, 2.dp).background(Color.White))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Notifications",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(notifications) { index, item ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { visible = true }
                
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically { it * (index + 1) / 2 }
                ) {
                    NotificationCard(item)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) Color.White.copy(alpha = 0.7f) else Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        if (notification.isRead) Color.Gray.copy(alpha = 0.3f) else Color(0xFF1B5E37),
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (notification.isRead) Color.Gray else Color.Black
                )
                Text(
                    text = notification.message,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.time,
                    fontSize = 12.sp,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    RemoteControlTheme {
        NotificationsScreen()
    }
}
