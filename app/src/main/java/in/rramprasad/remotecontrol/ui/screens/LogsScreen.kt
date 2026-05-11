package `in`.rramprasad.remotecontrol.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.rramprasad.remotecontrol.R
import `in`.rramprasad.remotecontrol.ui.theme.RemoteControlTheme

@Composable
fun LogsScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
        bottomBar = { EspRemoteBottomNavigation(currentScreen = "logs", onScreenSelected = onScreenSelected) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            SimpleHeader(title = stringResource(R.string.nav_logs))
            
            Column(modifier = Modifier.padding(16.dp)) {
                LogItem(time = "10:45 AM", event = "LED Light turned ON", type = "Manual")
                Spacer(modifier = Modifier.height(12.dp))
                LogItem(time = "09:30 AM", event = "Soil moisture dropped below 30%", type = "Alert")
                Spacer(modifier = Modifier.height(12.dp))
                LogItem(time = "08:15 AM", event = "System Rebooted", type = "System")
            }
        }
    }
}

@Composable
fun LogItem(time: String, event: String, type: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        when(type) {
                            "Alert" -> Color.Red
                            "System" -> Color.Blue
                            else -> Color(0xFF1B5E37)
                        },
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = event, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "$time • $type", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogsScreenPreview() {
    RemoteControlTheme {
        LogsScreen()
    }
}
