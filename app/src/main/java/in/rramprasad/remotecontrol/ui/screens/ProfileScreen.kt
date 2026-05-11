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
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
        bottomBar = { EspRemoteBottomNavigation(currentScreen = "profile", onScreenSelected = onScreenSelected) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            SimpleHeader(title = stringResource(R.string.nav_profile))
            
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFF1B5E37), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "R", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Ram Prasad", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "ramprasad@example.com", color = Color.Gray, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(32.dp))
                ProfileMenuItem(title = "Account Settings")
                ProfileMenuItem(title = "Notification Preferences")
                ProfileMenuItem(title = "Device Management")
                ProfileMenuItem(title = "Security & Privacy")
                
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E37)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text(text = "Logout", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(title: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontWeight = FontWeight.Medium)
            // Arrow icon placeholder
            Box(modifier = Modifier.size(16.dp).background(Color.Gray.copy(alpha = 0.3f), CircleShape))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    RemoteControlTheme {
        ProfileScreen()
    }
}
