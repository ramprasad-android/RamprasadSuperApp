package `in`.rramprasad.remotecontrol.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun EspRemoteScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
        bottomBar = { EspRemoteBottomNavigation(currentScreen = "home", onScreenSelected = onScreenSelected) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color(0xFF1B5E37),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 16.dp),
            ) {
                // Refresh icon placeholder
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Transparent, CircleShape)
                        .padding(2.dp)
                ) {
                    // Simple refresh icon representation
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.9f), CircleShape)
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF1B5E37), CircleShape)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            EspRemoteHeader(onScreenSelected = onScreenSelected)
            
            Column(modifier = Modifier.padding(16.dp)) {
                SectionTitle(title = stringResource(R.string.section_devices))
                DevicesSection()
                
                Spacer(modifier = Modifier.height(24.dp))
                BrightnessSection()
                
                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(title = stringResource(R.string.section_quick_actions))
                QuickActionsSection()
                
                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(title = stringResource(R.string.section_sensor_readings))
                SensorReadingsSection()
                
                Spacer(modifier = Modifier.height(100.dp)) // Extra space for FAB and scrolling
            }
        }
    }
}

@Composable
fun EspRemoteHeader(onScreenSelected: (String) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1B5E37), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.title_esp_remote),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color.White.copy(alpha = 0.15f), CircleShape)
                ) {
                    // Sun icon placeholder
                    Box(modifier = Modifier.size(20.dp).background(Color.White.copy(alpha = 0.8f), CircleShape))
                }
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = { onScreenSelected("notifications") },
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color.White.copy(alpha = 0.15f), CircleShape)
                ) {
                    // Bell icon placeholder
                    Box(
                        modifier = Modifier
                            .size(18.dp, 22.dp)
                            .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            color = Color.White.copy(alpha = 0.15f),
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(8.dp).background(Color.White, CircleShape))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${stringResource(R.string.device_id)} • ${stringResource(R.string.device_ip)} • ${stringResource(R.string.connection_type)}",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color(0xFF2E7D32).copy(alpha = 0.7f),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun DevicesSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            DeviceItem(
                icon = { Box(modifier = Modifier.size(18.dp).background(Color(0xFFFFD54F), CircleShape)) },
                iconBgColor = Color(0xFFFFF9C4),
                title = stringResource(R.string.device_led_light),
                subtitle = stringResource(R.string.status_led_light),
                isOn = true
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF1F8F1).copy(alpha = 0.5f))
            DeviceItem(
                icon = { Box(modifier = Modifier.size(18.dp).background(Color(0xFF4FC3F7), CircleShape)) },
                iconBgColor = Color(0xFFE3F2FD),
                title = stringResource(R.string.device_water_pump),
                subtitle = stringResource(R.string.status_water_pump),
                isOn = false
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF1F8F1).copy(alpha = 0.5f))
            DeviceItem(
                icon = { Box(modifier = Modifier.size(18.dp).background(Color(0xFF9575CD), CircleShape)) },
                iconBgColor = Color(0xFFF3E5F5),
                title = stringResource(R.string.device_exhaust_fan),
                subtitle = stringResource(R.string.status_exhaust_fan),
                isOn = false
            )
        }
    }
}

@Composable
fun DeviceItem(
    icon: @Composable () -> Unit,
    iconBgColor: Color,
    title: String,
    subtitle: String,
    isOn: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = iconBgColor,
            modifier = Modifier.size(48.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                icon()
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        Switch(
            checked = isOn,
            onCheckedChange = { },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF1B5E37),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFC8E6C9)
            )
        )
    }
}

@Composable
fun BrightnessSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF1F8F1), CircleShape)
                            .padding(8.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.6f), CircleShape))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = stringResource(R.string.label_led_brightness), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Text(text = stringResource(R.string.value_brightness_72), fontWeight = FontWeight.Bold, color = Color(0xFF1B5E37), fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Slider(
                value = 0.72f,
                onValueChange = { },
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color(0xFF1B5E37),
                    inactiveTrackColor = Color(0xFFC8E6C9)
                ),
                modifier = Modifier.height(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.value_0), fontSize = 12.sp, color = Color.Gray, modifier = Modifier.weight(1f))
                Text(text = stringResource(R.string.value_50), fontSize = 12.sp, color = Color.Gray, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                Text(text = stringResource(R.string.value_100), fontSize = 12.sp, color = Color.Gray, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
            }
        }
    }
}

@Composable
fun QuickActionsSection() {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionItem(
                modifier = Modifier.weight(1f),
                icon = { Box(modifier = Modifier.size(16.dp).background(Color.White, CircleShape)) },
                text = stringResource(R.string.action_all_lights_on),
                containerColor = Color(0xFF1B5E37),
                contentColor = Color.White
            )
            QuickActionItem(
                modifier = Modifier.weight(1f),
                icon = { Box(modifier = Modifier.size(16.dp).background(Color(0xFFFFD54F), CircleShape)) },
                text = stringResource(R.string.action_night_mode),
                containerColor = Color(0xFFE8F5E9)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionItem(
                modifier = Modifier.weight(1f),
                icon = { Box(modifier = Modifier.size(16.dp).background(Color(0xFF4FC3F7), CircleShape)) },
                text = stringResource(R.string.action_water_now),
                containerColor = Color(0xFFE8F5E9)
            )
            QuickActionItem(
                modifier = Modifier.weight(1f),
                icon = { Box(modifier = Modifier.size(16.dp).background(Color(0xFFFF8A65), CircleShape)) },
                text = stringResource(R.string.action_all_off),
                containerColor = Color(0xFFE8F5E9)
            )
        }
    }
}

@Composable
fun QuickActionItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color = Color.Black
) {
    Card(
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = text, color = contentColor, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SensorReadingsSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        SensorReadingItem(
            modifier = Modifier.weight(1f),
            value = stringResource(R.string.value_moisture_68),
            label = stringResource(R.string.label_soil_moisture)
        )
        SensorReadingItem(
            modifier = Modifier.weight(1f),
            value = stringResource(R.string.value_temp_28),
            label = stringResource(R.string.label_temperature)
        )
    }
}

@Composable
fun SensorReadingItem(modifier: Modifier = Modifier, value: String, label: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E37))
            Text(text = label, fontSize = 15.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun EspRemoteBottomNavigation(
    currentScreen: String = "home",
    onScreenSelected: (String) -> Unit = {}
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 16.dp,
        modifier = Modifier.height(90.dp)
    ) {
        val selectedColor = Color(0xFF1B5E37)
        val unselectedColor = Color.Gray
        
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = { onScreenSelected("home") },
            icon = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(if (currentScreen == "home") Color(0xFFC8E6C9) else Color.Transparent, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(16.dp).background(if (currentScreen == "home") selectedColor else unselectedColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp)))
                }
            },
            label = { Text(stringResource(R.string.nav_home), fontWeight = if (currentScreen == "home") FontWeight.Bold else FontWeight.Normal, fontSize = 12.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                unselectedIconColor = unselectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentScreen == "devices",
            onClick = { onScreenSelected("devices") },
            icon = { Box(modifier = Modifier.size(24.dp).background(if (currentScreen == "devices") selectedColor.copy(alpha = 0.2f) else unselectedColor.copy(alpha = 0.2f), RoundedCornerShape(6.dp))) },
            label = { Text(stringResource(R.string.nav_devices), fontWeight = if (currentScreen == "devices") FontWeight.Bold else FontWeight.Normal, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = currentScreen == "logs",
            onClick = { onScreenSelected("logs") },
            icon = { Box(modifier = Modifier.size(24.dp).background(if (currentScreen == "logs") selectedColor.copy(alpha = 0.2f) else unselectedColor.copy(alpha = 0.2f), RoundedCornerShape(6.dp))) },
            label = { Text(stringResource(R.string.nav_logs), fontWeight = if (currentScreen == "logs") FontWeight.Bold else FontWeight.Normal, fontSize = 12.sp) }
        )
        NavigationBarItem(
            selected = currentScreen == "profile",
            onClick = { onScreenSelected("profile") },
            icon = { Box(modifier = Modifier.size(24.dp).background(if (currentScreen == "profile") selectedColor.copy(alpha = 0.2f) else unselectedColor.copy(alpha = 0.2f), RoundedCornerShape(6.dp))) },
            label = { Text(stringResource(R.string.nav_profile), fontWeight = if (currentScreen == "profile") FontWeight.Bold else FontWeight.Normal, fontSize = 12.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EspRemoteScreenPreview() {
    RemoteControlTheme {
        EspRemoteScreen()
    }
}
