package `in`.rramprasad.remotecontrol.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import kotlinx.coroutines.delay

@Composable
fun DevicesScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    var isScanning by remember { mutableStateOf(false) }
    
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F8F1),
        bottomBar = { EspRemoteBottomNavigation(currentScreen = "devices", onScreenSelected = onScreenSelected) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SimpleHeader(title = stringResource(R.string.nav_devices))
            
            Column(modifier = Modifier.padding(16.dp)) {
                ConnectivityTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                if (selectedTab == 0) {
                    ActiveDevicesContent()
                } else {
                    NearbyDevicesContent(
                        isBluetooth = selectedTab == 1,
                        isScanning = isScanning,
                        onScanToggle = { isScanning = it }
                    )
                }
            }
        }
    }
}

@Composable
fun ConnectivityTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.Transparent,
        contentColor = Color(0xFF1B5E37),
        edgePadding = 0.dp,
        divider = {},
        indicator = { tabPositions ->
            if (selectedTab < tabPositions.size) {
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .height(3.dp)
                        .padding(horizontal = 16.dp)
                        .background(Color(0xFF1B5E37), RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                )
            }
        }
    ) {
        Tab(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            text = { Text("My Devices", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
        )
        Tab(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            text = { Text("Bluetooth", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
        )
        Tab(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            text = { Text("Wi-Fi", fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal) }
        )
    }
}

@Composable
fun ActiveDevicesContent() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        SectionTitle(title = "ACTIVE DEVICES")
        DevicesSection()
        
        Spacer(modifier = Modifier.height(24.dp))
        SectionTitle(title = "OFFLINE DEVICES")
        AvailableDevicesSection()
    }
}

@Composable
fun NearbyDevicesContent(
    isBluetooth: Boolean,
    isScanning: Boolean,
    onScanToggle: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionTitle(title = if (isBluetooth) "NEARBY BLUETOOTH DEVICES" else "AVAILABLE WI-FI NETWORKS")
            TextButton(onClick = { onScanToggle(!isScanning) }) {
                Text(if (isScanning) "Stop Scan" else "Scan", color = Color(0xFF1B5E37), fontWeight = FontWeight.Bold)
            }
        }

        if (isScanning) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color(0xFFC8E6C9), RoundedCornerShape(2.dp))
            ) {
                var progress by remember { mutableStateOf(0f) }
                LaunchedEffect(Unit) {
                    while (true) {
                        progress += 0.05f
                        if (progress > 1f) progress = 0f
                        delay(50)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .background(Color(0xFF1B5E37), RoundedCornerShape(2.dp))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        val mockDevices = remember(isBluetooth) {
            if (isBluetooth) {
                listOf(
                    NearbyDevice("ESP32-Smart-Hub", "Pairing...", "80%"),
                    NearbyDevice("Living Room Light", "Available", "65%"),
                    NearbyDevice("Kitchen Speaker", "Available", "40%")
                )
            } else {
                listOf(
                    NearbyDevice("Home-WiFi-5G", "Connected", "90%"),
                    NearbyDevice("TP-Link_Guest", "Secured", "70%"),
                    NearbyDevice("ESP-AP-Config", "Open", "100%")
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mockDevices) { device ->
                NearbyDeviceItem(device, isBluetooth)
            }
        }
    }
}

data class NearbyDevice(val name: String, val status: String, val signal: String)

@Composable
fun NearbyDeviceItem(device: NearbyDevice, isBluetooth: Boolean) {
    var isConnecting by remember { mutableStateOf(false) }
    var connectionProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(isConnecting) {
        if (isConnecting) {
            connectionProgress = 0f
            while (connectionProgress < 1f) {
                delay(100)
                connectionProgress += 0.05f
            }
            isConnecting = false
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF1F8F1),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFF1B5E37).copy(alpha = 0.6f), CircleShape)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = device.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = if (isConnecting) "Connecting..." else device.status, color = Color.Gray, fontSize = 12.sp)
                }
                
                if (isConnecting) {
                    CircularProgressIndicator(
                        progress = { connectionProgress },
                        modifier = Modifier.size(24.dp),
                        color = Color(0xFF1B5E37),
                        strokeWidth = 3.dp,
                    )
                } else {
                    Button(
                        onClick = { isConnecting = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E37)),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(if (isBluetooth) "Pair" else "Connect", fontSize = 12.sp)
                    }
                }
            }
            
            if (isConnecting) {
                LinearProgressIndicator(
                    progress = { connectionProgress },
                    modifier = Modifier.fillMaxWidth().height(2.dp),
                    color = Color(0xFF1B5E37),
                    trackColor = Color(0xFFC8E6C9)
                )
            }
        }
    }
}

@Composable
fun SimpleHeader(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1B5E37), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AvailableDevicesSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            DeviceItem(
                icon = { Box(modifier = Modifier.size(18.dp).background(Color.Gray, CircleShape)) },
                iconBgColor = Color(0xFFF1F8F1),
                title = "Smart Plug",
                subtitle = "Offline",
                isOn = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DevicesScreenPreview() {
    RemoteControlTheme {
        DevicesScreen()
    }
}
